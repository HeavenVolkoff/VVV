package Rest.Controller;

import Rest.Model.tables.daos.UserDao;
import Rest.Model.tables.pojos.User;
import Server.DbFactory;
import org.hibernate.validator.constraints.Email;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("user")
public class UserController {

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public User get(){
        try(DbFactory.Db db = DbFactory.getInstance().open()){ //Try with resources
            UserDao userDao = new UserDao(db.getConfiguration());
            return userDao.findById(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.TEXT_PLAIN})
    public String create(
            @NotNull @FormParam("firstName") String firstName,
            @NotNull @FormParam("lastName") String lastName,
            @NotNull @FormParam("password") String password,
            @Email @FormParam("email") String email
    ) {
        try(DbFactory.Db db = DbFactory.getInstance().open()){
            byte[] passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(12)).getBytes();
            UserDao userDao = new UserDao(db.getConfiguration());
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPasswordHash(passwordHash);

            userDao.insert(user);

            return "ok";

        } catch (Exception e) {
            e.printStackTrace();

            return "not ok";
        }
    }
}