package Rest.Controller;

import Rest.Model.tables.daos.UserDao;
import Rest.Model.tables.pojos.User;
import Server.DbFactory;
import org.glassfish.hk2.api.PerLookup;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@PerLookup
@Path("passageiro")
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
}