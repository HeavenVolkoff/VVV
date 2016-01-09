package Server;

import HTML.Controller.ViewController;
import Rest.Controller.UserController;

import Server.ErrorHandling.AppExceptionMapper;
import Server.ErrorHandling.GenericExceptionMapper;
import Server.ErrorHandling.JsonMappingExceptionMapper;
import Server.ErrorHandling.NotFoundExceptionMapper;
import Server.Filter.CsrfCustomProtectionFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.servlet.ServletProperties;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Application extends ResourceConfig {
    public static final String CSRF_KEY        = "CSRF_KEY_VVV_SYSTEM_176907663";
    public static final String EMPLOYEE_KEY    = "EMPLOYEE_KEY_VVV_SYSTEM_733674525";
    public static final String MANAGER_KEY     = "MANAGER_KEY_VVV_SYSTEM_130496760";
    private final static Logger LOGGER;

    static{
        LOGGER = Logger.getLogger("VVV");
        LOGGER.setUseParentHandlers(false);

        // remove handlers that will be replaced
        Handler[] handlers = LOGGER.getHandlers();
        for(Handler handler : handlers)
        {
            if(handler.getClass() == ConsoleHandler.class)
                LOGGER.removeHandler(handler);
        }

        LOGGER.addHandler(new ConsoleHandler(){{
            setLevel(Level.CONFIG);
        }});
        LOGGER.setLevel(Level.CONFIG);
    }

    public static Logger getLogger(String name){
        return Logger.getLogger("VVV." + name);
    }

    public Application() {// Resources.
        super(
                CsrfCustomProtectionFilter.class,
                GenericExceptionMapper.class,
                JsonMappingExceptionMapper.class,
                NotFoundExceptionMapper.class,
                AppExceptionMapper.class,
                FreemarkerMvcFeature.class,
                ViewController.class,
                UserController.class
        );

        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}
