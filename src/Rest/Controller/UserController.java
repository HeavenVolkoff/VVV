package Rest.Controller;

import Rest.Model.tables.daos.UserDao;
import Rest.Model.tables.pojos.User;
import Server.ErrorHandling.AppException;
import Server.Utility.DbFactory;
import Server.Utility.ValidatorFactory;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.jooq.tools.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.ConstraintViolation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.*;


@Path("user")
public class UserController {

    private static final JsonNodeFactory jsonFactory = JsonNodeFactory.instance;

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public User get() throws AppException {
        try(DbFactory.Db db = DbFactory.openConnection()){ //Try with resources
            UserDao userDao = new UserDao(db.getConfiguration());
            return userDao.findById(1);

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(500, 500, "Server Internal Error", e.getMessage(), "");
        }
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public JSONObject create(User user) throws AppException {
        JSONObject obj = new JSONObject();
        obj.put("type", "Message");

        try(DbFactory.Db db = DbFactory.openConnection()){ //Try with resources
            Set<ConstraintViolation<User>> constraintViolations = ValidatorFactory.getValidator().validate(user);

            if(constraintViolations.size() != 0){
                ConstraintViolation validationError = constraintViolations.iterator().next();
                obj.put("successful", false);
                obj.put("field", validationError.getPropertyPath().toString());
                obj.put("error", validationError.getMessage());

            }else{
                user.setCreatedAt(new Timestamp((new Date()).getTime()));
                UserDao userDao = new UserDao(db.getConfiguration());

                if(userDao.fetchOneByEmail(user.getEmail()) != null){
                    obj.put("successful", false);
                    obj.put("field", "email");
                    obj.put("error", "DUPLICATE_EMAIL");

                }else{
                    userDao.insert(user);
                    obj.put("successful", true);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(500, 500, "Server Internal Error", e.getMessage(), "");
        }

        return obj;
    }

    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public JSONObject login(HashMap<String, String> credentials) throws AppException {
        JSONObject obj = new JSONObject();
        obj.put("type", "Login");

        login: try(DbFactory.Db db = DbFactory.openConnection()) { //Try with resources
            UserDao userDao = new UserDao(db.getConfiguration());
            String password = credentials.get("password");
            String email = credentials.get("email");

            if(password != null && email != null){
                User user = userDao.fetchOneByEmail(email);

                if(user != null){
                    byte[] passwordHash = user.getPasswordHash();
                    String passwordHashString = new String(passwordHash, "UTF-8");

                    if(BCrypt.checkpw(password, passwordHashString)){
                        byte[] uniqueIdentifier = BCrypt.hashpw(user.getFirstName() + user.getEmail() + user.getLastName() + user.getCreatedAt(), BCrypt.gensalt(12)).getBytes();//length 60
                        byte[] randomIdentifier = UUID.randomUUID().toString().getBytes();//length: 36
                        byte[] token = new byte[uniqueIdentifier.length + randomIdentifier.length];//length: 96
                        //TODO: Save token on DB to be able to verify to later
                        System.arraycopy(uniqueIdentifier, 0, token, 0, uniqueIdentifier.length);
                        System.arraycopy(randomIdentifier, 0, token, uniqueIdentifier.length, randomIdentifier.length);

                        obj.put("successful", true);
                        obj.put("token", Base64.getEncoder().encodeToString(token));//length: 129

                        break login;
                    }

                    obj.put("error", "INVALID");

                }else{
                    obj.put("error", "NOT_EXISTS");
                }
            }else{
                obj.put("error", "NOT_NULL");
            }

            obj.put("successful", false);

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(500, 500, "Server Internal Error", e.getMessage(), "");
        }

        return obj;
    }
}