package Rest.Controller;

import Rest.Model.tables.daos.UserDao;
import Rest.Model.tables.pojos.User;
import Server.Utility.DbFactory;
import Server.Utility.ValidatorFactory;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.jooq.tools.json.JSONObject;

import javax.validation.ConstraintViolation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Path("user")
public class UserController {

    private static final JsonNodeFactory jsonFactory = JsonNodeFactory.instance;

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(){
        try(DbFactory.Db db = DbFactory.openConnection()){ //Try with resources
            UserDao userDao = new UserDao(db.getConfiguration());
            return userDao.findById(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public JSONObject create(User user){
        JSONObject obj = new JSONObject();

        try(DbFactory.Db db = DbFactory.openConnection()){ //Try with resources
            Set<ConstraintViolation<User>> constraintViolations = ValidatorFactory.getValidator().validate(user);

            if(constraintViolations.size() != 0){
                obj.put("successful", false);
                obj.put("error", constraintViolations.iterator().next().getMessage());

            }else{
                user.setCreatedAt(new Timestamp((new Date()).getTime()));
                UserDao userDao = new UserDao(db.getConfiguration());
                userDao.insert(user);

                obj.put("successful", true);
            }

        } catch (Exception e) {
            e.printStackTrace();

            obj.put("successful", false);
            obj.put("error", "SERVER_ERROR");
        }

        return obj;
    }
}