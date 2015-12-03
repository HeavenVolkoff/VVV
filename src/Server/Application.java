package Server;

import Rest.Controller.UserController;

import Server.ErrorHandling.AppExceptionMapper;
import Server.ErrorHandling.GenericExceptionMapper;
import Server.ErrorHandling.JsonMappingExceptionMapper;
import Server.ErrorHandling.NotFoundExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.CsrfProtectionFilter;


public class Application extends ResourceConfig {
    public Application() {// Resources.
        super(
                CsrfProtectionFilter.class,
                UserController.class,
                GenericExceptionMapper.class,
                JsonMappingExceptionMapper.class,
                NotFoundExceptionMapper.class,
                AppExceptionMapper.class
        );
    }
}
