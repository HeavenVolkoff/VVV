package Rest.Controller;

import Rest.Model.tables.daos.UserDao;
import Rest.Model.tables.pojos.User;
import Server.Utility.DbFactory;
import Server.Utility.ValidatorFactory;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.ConstraintViolation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;


@Path("user")
public class UserController {

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
    public String create(User user){
        try(
                DbFactory.Db db = DbFactory.openConnection();
                ValidatorFactory.Validator validator = ValidatorFactory.getValidator())
        {
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

            if(constraintViolations.size() != 0){
                System.out.println("Erro Verificacao");
                return constraintViolations.iterator().next().getMessage();
            }

            UserDao userDao = new UserDao(db.getConfiguration());
            userDao.insert(user);

            return "ok";

        } catch (Exception e) {
            e.printStackTrace();
            //TODO: 500 - Server Error
        }

        return "not ok";
    }
}