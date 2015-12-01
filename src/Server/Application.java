package Server;

import Rest.Controller.UserController;

import org.glassfish.jersey.server.ResourceConfig;


public class Application extends ResourceConfig {
    public Application() {// Resources.
        packages(UserController.class.getPackage().getName());
    }
}
