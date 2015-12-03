package Rest.Controller;

import Rest.Model.tables.daos.OauthDao;
import Rest.Model.tables.daos.UserDao;
import Rest.Model.tables.pojos.Oauth;
import Rest.Model.tables.pojos.User;
import Server.ErrorHandling.AppException;
import Server.ResponseMessage;
import Server.Utility.DbFactory;
import Server.Utility.FactoryException;
import Server.Utility.ValidatorFactory;
import org.mindrot.jbcrypt.BCrypt;

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
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage get(@QueryParam("id") int id) throws AppException {

        try(DbFactory.Db db = DbFactory.openConnection()){ //Try with resources
            UserDao userDao = new UserDao(db.getConfiguration());
            User user = userDao.findById(id);

            if(user == null){
                throw new AppException(404, "USER_NOT_FOUND", "User Not Found");
            }

            return new ResponseMessage("FOUND_USER", user);

        } catch (SQLException | FactoryException e) {
            throw new AppException(500, "DB_ERROR", e.getStackTrace(), e.getMessage());
        }
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(User user) throws AppException {
        HashMap<String, String> errorData = new HashMap<>();

        try(DbFactory.Db db = DbFactory.openConnection()){ //Try with resources
            Set<ConstraintViolation<User>> constraintViolations = ValidatorFactory.getValidator().validate(user);

            if(constraintViolations.size() != 0){
                ConstraintViolation validationError = constraintViolations.iterator().next();
                errorData.put("field", validationError.getPropertyPath().toString());
                errorData.put("error", validationError.getMessage());

                throw new AppException(400, "VALIDATION_ERROR", errorData);
            }

            UserDao userDao = new UserDao(db.getConfiguration());
            if(userDao.fetchOneByEmail(user.getEmail()) != null){
                errorData.put("field", "email");
                errorData.put("error", "DUPLICATE_EMAIL");

                throw new AppException(400, "VALIDATION_ERROR", errorData);
            }

            user.setCreatedAt(new Timestamp((new Date()).getTime()));
            user.setType((byte) 2);
            userDao.insert(user);

            return Response.status(202).entity(new ResponseMessage(202, "USER_CREATED", "WAITING_EMAIL_CONFIRMATION")).build();

        } catch (SQLException | FactoryException e) {
            throw new AppException(500, "DB_ERROR", e.getStackTrace(), e.getMessage());
        }
    }

    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseMessage login(HashMap<String, String> credentials) throws AppException {
        try(DbFactory.Db db = DbFactory.openConnection()) { //Try with resources
            UserDao userDao = new UserDao(db.getConfiguration());
            String password = credentials.get("password");
            String email = credentials.get("email");

            if(password == null || email == null){
                throw new AppException(400, "INVALID_CREDENTIALS", "Username or Password are Invalid");
            }

            User user = userDao.fetchOneByEmail(email);

            if(user == null){
                throw new AppException(400, "INVALID_EMAIL", "There is no user associated with this email");
            }

            byte[] passwordHash = user.getPasswordHash();
            String passwordHashString = new String(passwordHash, "UTF-8");

            if(!(BCrypt.checkpw(password, passwordHashString))){
                throw new AppException(401, "INVALID_CREDENTIALS", "Username or Password are Invalid");
            }

            OauthDao authorizationDao = new OauthDao(db.getConfiguration());
            Oauth oldAuth = authorizationDao.fetchOneByUserId(user.getId());

            if(oldAuth != null){
                if(oldAuth.getCreatedAt().getTime() - (new Date()).getTime() < MILLISECONDS.convert(1, DAYS)){
                    return new ResponseMessage("LOGGED_IN", Base64.getEncoder().encodeToString(oldAuth.getToken()));

                }else{
                    authorizationDao.delete(oldAuth);
                }
            }

            byte[] uniqueIdentifier =
                    BCrypt.hashpw(
                            user.getFirstName() + user.getEmail() + user.getLastName() + user.getCreatedAt(),
                            BCrypt.gensalt(12)
                    ).getBytes();//length 60
            byte[] randomIdentifier = UUID.randomUUID().toString().getBytes();//length: 36
            byte[] token = new byte[uniqueIdentifier.length + randomIdentifier.length];//length: 96

            System.arraycopy(uniqueIdentifier, 0, token, 0, uniqueIdentifier.length);
            System.arraycopy(randomIdentifier, 0, token, uniqueIdentifier.length, randomIdentifier.length);

            Oauth authorization = new Oauth(token, new Timestamp((new Date()).getTime()), user.getId());
            authorizationDao.insert(authorization);

            return new ResponseMessage("LOGGED_IN", Base64.getEncoder().encodeToString(token));

        } catch (SQLException | FactoryException e) {
            throw new AppException(500, "DB_ERROR", e.getStackTrace(), e.getMessage());

        } catch (UnsupportedEncodingException e){
            throw new AppException(401, "INVALID_ENCODING", "Username or Password have invalid encoding");
        }
    }
}