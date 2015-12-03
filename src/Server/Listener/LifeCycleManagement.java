package Server.Listener;

import Server.Utility.DbFactory;
import Server.Utility.ValidatorFactory;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.ext.Provider;

@Provider
public class LifeCycleManagement implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Started...");
        System.out.println(BCrypt.hashpw("CSRF_KEY_VVV_SYSTEM_176907663", BCrypt.gensalt(10)));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Finished...");

        DbFactory.closeAllConnections();
    }
}
