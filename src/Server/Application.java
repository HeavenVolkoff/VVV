package Server;

import Rest.Controller.UserController;

import Server.ErrorHandling.AppExceptionMapper;
import Server.ErrorHandling.GenericExceptionMapper;
import Server.ErrorHandling.NotFoundExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;


public class Application extends ResourceConfig {
    public Application() {// Resources.
        packages(UserController.class.getPackage().getName());
        packages(GenericExceptionMapper.class.getPackage().getName());
        packages(NotFoundExceptionMapper.class.getPackage().getName());
        packages(AppExceptionMapper.class.getPackage().getName());
    }
}
