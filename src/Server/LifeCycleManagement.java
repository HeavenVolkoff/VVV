package Server;

import Server.Utility.DbFactory;
import Server.Utility.ValidatorFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.ext.Provider;

@Provider
public class LifeCycleManagement implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Started...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Finished...");

        DbFactory.closeAllConnections();
    }
}
