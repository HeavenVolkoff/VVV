package Server;

import Rest.Controller.UserController;

import Server.ErrorHandling.AppExceptionMapper;
import Server.ErrorHandling.GenericExceptionMapper;
import Server.ErrorHandling.JsonMappingExceptionMapper;
import Server.ErrorHandling.NotFoundExceptionMapper;
import Server.Filter.CsrfCustomProtectionFilter;
import org.glassfish.jersey.server.ResourceConfig;


public class Application extends ResourceConfig {
    public static final String CSRF_KEY        = "CSRF_KEY_VVV_SYSTEM_176907663";
    public static final String EMPLOYEE_KEY    = "EMPLOYEE_KEY_VVV_SYSTEM_733674525";
    public static final String MANAGER_KEY     = "MANAGER_KEY_VVV_SYSTEM_130496760";

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
