package Rest.Controller;

import Rest.Model.tables.daos.OauthDao;
import Rest.Model.tables.pojos.Oauth;
import Server.ErrorHandling.AppException;
import Server.Utility.ResponseMessage;
import Server.Utility.DbFactory;
import Server.Utility.FactoryException;
import Server.Utility.ValidatorFactory;
import org.jooq.Configuration;
import org.mindrot.jbcrypt.BCrypt;

import Rest.Model.daoExtended.UserDao;
import Rest.Model.pojoExtended.User;

import javax.validation.ConstraintViolation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import static java.util.concurrent.TimeUnit.*;

@Path("user")
public class UserController {

    @GET
    @Path("ping")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage ping(@HeaderParam("AuthToken") String token, @QueryParam("id") int userId) throws AppException {
        try(DbFactory.Db db = DbFactory.openConnection()){ //Try with resources
            AssertUserIsLoggedIn(userId, token, db.getConfiguration()); //This will upgrade oAuth time for us

            return new ResponseMessage("PONG", "It's Alive");

        } catch (SQLException | FactoryException e) {
            throw new AppException(Response.Status.INTERNAL_SERVER_ERROR, "DB_ERROR", e.getStackTrace(), e.getMessage());
        }
    }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage get(@HeaderParam("AuthToken") String token, @QueryParam("id") int userId) throws AppException {
        try(DbFactory.Db db = DbFactory.openConnection()){ //Try with resources
            AssertUserIsLoggedIn(userId, token, db.getConfiguration());

            User user = new UserDao(db.getConfiguration()).findById(userId); //Get user

            if(user == null){
                throw new AppException(Response.Status.NOT_FOUND, "USER_NOT_FOUND", "User Not Found");
            }

            return new ResponseMessage("FOUND_USER", user); //Return User non-sensible info

        } catch (SQLException | FactoryException e) {
            throw new AppException(Response.Status.INTERNAL_SERVER_ERROR, "DB_ERROR", e.getStackTrace(), e.getMessage());
        }
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(User user) throws AppException {
        try(DbFactory.Db db = DbFactory.openConnection()){ //Try with resources
            HashMap<String, String> errorData = new HashMap<>(); //JSON container
            Set<ConstraintViolation<User>> constraintViolations = ValidatorFactory.getValidator().validate(user); //Check validations errors

            if(!constraintViolations.isEmpty()){ //If we have errors return them to user
                ConstraintViolation validationError = constraintViolations.iterator().next();
                errorData.put("field", validationError.getPropertyPath().toString());
                errorData.put("error", validationError.getMessage());

                throw new AppException(Response.Status.BAD_REQUEST, "VALIDATION_ERROR", errorData);
            }

            UserDao userDao = new UserDao(db.getConfiguration());
            if(userDao.fetchOneByEmail(user.getEmail()) != null){ //Check if we don't already have a user with the same email address
                errorData.put("field", "email");
                errorData.put("error", "DUPLICATE_EMAIL");

                throw new AppException(Response.Status.BAD_REQUEST, "VALIDATION_ERROR", errorData);
            }

            if(!user.checkType()){ //Check if this user have a valid key to be of type employer
                errorData.put("field", "type");
                errorData.put("error", "INVALID_KEY");

                throw new AppException(Response.Status.BAD_REQUEST, "VALIDATION_ERROR", errorData);
            }

            userDao.insert(user); //Insert new user on database

            return ResponseMessage.build(Response.Status.ACCEPTED, "USER_CREATED", "WAITING_EMAIL_CONFIRMATION"); //Return success message

        } catch (SQLException | FactoryException e) {
            throw new AppException(Response.Status.INTERNAL_SERVER_ERROR, "DB_ERROR", e.getStackTrace(), e.getMessage());
        }
    }

    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseMessage login(HashMap<String, String> credentials) throws AppException {
        try(DbFactory.Db db = DbFactory.openConnection()) { //Try with resources
            String password = credentials.get("password");
            String email = credentials.get("email");

            if(password == null || email == null){
                throw new AppException(Response.Status.BAD_REQUEST, "INVALID_CREDENTIALS", "Username or Password are Invalid");
            }

            User user = new UserDao(db.getConfiguration()).fetchOneByEmail(email); //Get user

            if(user == null){
                throw new AppException(Response.Status.BAD_REQUEST, "INVALID_EMAIL", "There is no user associated with this email");
            }

            if(!user.checkPassword(password)){ //Check password
                throw new AppException(Response.Status.UNAUTHORIZED, "INVALID_CREDENTIALS", "Username or Password are Invalid");
            }

            byte[] token = generateOAuthToken(user, db.getConfiguration());

            return new ResponseMessage("LOGGED_IN", new HashMap<String, Object>(){{ //Return oAuth token to user
                put("token", Base64.getEncoder().encodeToString(token));
                put("userId", user.getId());
            }});

        } catch (SQLException | FactoryException e) {
            throw new AppException(Response.Status.INTERNAL_SERVER_ERROR, "DB_ERROR", e.getStackTrace(), e.getMessage());

        } catch (UnsupportedEncodingException e){
            throw new AppException(Response.Status.UNAUTHORIZED, "INVALID_ENCODING", "Username or Password have invalid encoding");
        }
    }

    //########################### Private Methods ###############################

    private byte[] generateOAuthToken(User user, Configuration dbConfig){
        OauthDao authDao = new OauthDao(dbConfig);
        Oauth auth = authDao.fetchOneByUserId(user.getId());
        long now = (new Date()).getTime();

        if(auth != null){
            if(now - auth.getLastLoginAt().getTime() < MILLISECONDS.convert(1, HOURS)){
                return auth.getToken();

            }else{
                authDao.delete(auth);
                auth.setLastLoginAt(new Timestamp(now));
            }

        }else{
            auth = new Oauth(null, new Timestamp(now), user.getId());
        }

        byte[] uniqueIdentifier =
                BCrypt.hashpw(
                        user.getFirstName() + user.getEmail() + user.getLastName() + user.getCreatedAt().getTime(),
                        BCrypt.gensalt(12)
                ).getBytes();//length 60
        byte[] randomIdentifier = UUID.randomUUID().toString().getBytes();//length: 36
        byte[] token = new byte[uniqueIdentifier.length + randomIdentifier.length];//length: 96

        System.arraycopy(uniqueIdentifier, 0, token, 0, uniqueIdentifier.length);
        System.arraycopy(randomIdentifier, 0, token, uniqueIdentifier.length, randomIdentifier.length);

        auth.setToken(token);
        authDao.insert(auth);

        return token;
    }

    private void AssertUserIsLoggedIn(int id, String token, Configuration dbConfig) throws AppException {
        long now = (new Date()).getTime();

        if(token == null){
            throw new AppException(Response.Status.UNAUTHORIZED, "NOT_NULL", "Token is required");
        }

        OauthDao authDao = new OauthDao(dbConfig);
        Oauth auth = authDao.fetchOneByUserId(id);

        if(auth == null){
            throw new AppException(Response.Status.UNAUTHORIZED, "USER_NOT_LOGGED", "User isn't logged in");
        }

        if(!Arrays.equals(auth.getToken(), Base64.getDecoder().decode(token))){
            throw new AppException(Response.Status.UNAUTHORIZED, "INVALID_TOKEN", "Token is invalid");

        } else if(now - auth.getLastLoginAt().getTime() > MILLISECONDS.convert(1, HOURS)){
            throw new AppException(Response.Status.UNAUTHORIZED, "EXPIRED_TOKEN", "Token is expired");
        }

        auth.setLastLoginAt(new Timestamp(now));
        authDao.update(auth);
    }
}