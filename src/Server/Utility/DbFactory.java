package Server.Utility;

import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;


public final class DbFactory {
    private static DbFactory instance = null;
    private static final Object mutex= new Object();

    public static final class Db implements AutoCloseable{
        private Connection connection;
        private Configuration configuration;
        private UUID id;

        private Db(Connection connection, UUID id){
            this.connection = connection;
            this.id = id;
            this.configuration = new DefaultConfiguration().set(this.connection).set(SQLDialect.MYSQL);
        }

        @Override
        public void close() throws Exception{
            if(!instance.closing.get()){
                Connection connection = instance.busyConnections.remove(this.id);

                if(!connection.isClosed()){
                    instance.idleConnections.addFirst(connection);
                }
            }
        }

        public Connection getConnection() {
            return connection;
        }

        public Configuration getConfiguration() {
            return configuration;
        }
    }

    public static Db openConnection() throws SQLException, FactoryException {
        if(instance == null){
            synchronized (mutex){
                if(instance == null){
                    instance = new DbFactory();
                }
            }
        }

        if(instance.closing.get()){
            throw new FactoryException("DB Shutting Down");
        }

        Connection reserved = instance.idleConnections.pollLast();
        UUID id = UUID.randomUUID();

        if(reserved == null || reserved.isClosed()){
            reserved = DriverManager.getConnection(instance.url, instance.username, instance.password);
        }

        instance.busyConnections.put(id, reserved);

        return new Db(reserved, id);
    }

    public static void closeAllConnections(){
        if(instance.closing.compareAndSet(false, true)){
            for(Connection connection : instance.busyConnections.values()){
                try{
                    if(!connection.isClosed()){
                        connection.close();
                    }
                }catch (SQLException e){/*Log*/}
            }

            for (Connection connection : instance.idleConnections) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {/*Log*/}
            }

            instance.busyConnections.clear();
            instance.idleConnections.clear();
        }
    }

    private String url;
    private String username;
    private String password;

    private ConcurrentHashMap<UUID, Connection> busyConnections;
    private ConcurrentLinkedDeque<Connection> idleConnections;

    private AtomicBoolean closing;

    //TODO: implement this later
    private TimerTask clearTask;

    private DbFactory() {
        this.url = "jdbc:mysql://localhost:3306/vvv";
        this.username = "java";
        this.password = "";

        this.busyConnections = new ConcurrentHashMap<>();
        this.idleConnections = new ConcurrentLinkedDeque<>();

        this.closing = new AtomicBoolean();

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            /*Log*/
            //TODO: Answer All requests with database error page <- use jersey filter
        }
    }

    public void close(UUID id) throws SQLException {
        Connection idle = busyConnections.remove(id);

        if(!idle.isClosed()){
            idleConnections.addFirst(idle);
        }
    }
}
