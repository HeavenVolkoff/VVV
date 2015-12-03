package Server;

import Rest.Controller.UserController;

import Server.ErrorHandling.AppExceptionMapper;
import Server.ErrorHandling.GenericExceptionMapper;
import Server.ErrorHandling.JsonMappingExceptionMapper;
import Server.ErrorHandling.NotFoundExceptionMapper;
import Server.Filter.CsrfCustomProtectionFilter;
import org.glassfish.jersey.server.ResourceConfig;


public class Application extends ResourceConfig {
    public Application() {// Resources.
        super(
                CsrfCustomProtectionFilter.class,
                UserController.class,
                GenericExceptionMapper.class,
                JsonMappingExceptionMapper.class,
                NotFoundExceptionMapper.class,
                AppExceptionMapper.class
        );
    }
}
