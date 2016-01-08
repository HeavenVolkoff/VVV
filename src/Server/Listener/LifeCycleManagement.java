package Server.Listener;

import Server.Application;
import Server.Utility.DbFactory;
import Server.Utility.ValidatorFactory;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class LifeCycleManagement implements ServletContextListener {
    private final static Logger LOGGER;
    static{
        LOGGER = Logger.getLogger(LifeCycleManagement.class.getName());
        LOGGER.setLevel(Level.CONFIG);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Server started...");

        System.out.println("DEFAULT CSRF KEY: "      + BCrypt.hashpw(Application.CSRF_KEY,       BCrypt.gensalt(10)));
        System.out.println("DEFAULT EMPLOYEE KEY: "  + BCrypt.hashpw(Application.EMPLOYEE_KEY,   BCrypt.gensalt(10)));
        System.out.println("DEFAULT MANAGER KEY: "   + BCrypt.hashpw(Application.MANAGER_KEY,    BCrypt.gensalt(10)));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DbFactory.closeAllConnections();

        System.out.println("Server stopped...");
    }
}
