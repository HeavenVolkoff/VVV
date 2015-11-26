package Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class LifeCycleManagement implements ServletContextListener {

    private DbFactory db;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Started...");

        this.db = DbFactory.getInstance(); //Initialize DB at server star-up
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Finished...");

        this.db.closeAll();
    }
}
