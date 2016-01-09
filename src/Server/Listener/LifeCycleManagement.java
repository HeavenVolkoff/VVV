package Server.Listener;

import Server.Application;
import Server.Utility.DbFactory;
import Server.Utility.ValidatorFactory;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.ext.Provider;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class LifeCycleManagement implements ServletContextListener {
    private final static Logger LOGGER = Application.getLogger(LifeCycleManagement.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("Server started...");

        LOGGER.config("DEFAULT EMPLOYEE KEY: "  + BCrypt.hashpw(Application.EMPLOYEE_KEY,   BCrypt.gensalt(10)));
        LOGGER.config("DEFAULT MANAGER KEY: "   + BCrypt.hashpw(Application.MANAGER_KEY,    BCrypt.gensalt(10)));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DbFactory.closeAllConnections();

        LOGGER.info("Server stopped...");
    }
}
