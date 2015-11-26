package Server;

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


public class DbFactory {
    private static DbFactory instance = null;
    private static final Object mutex= new Object();

    public static DbFactory getInstance() {
        if(instance == null){
            synchronized (mutex){
                if(instance == null){
                    instance = new DbFactory();
                }
            }
        }

        return instance;
    }

    private String url;
    private String username;
    private String password;

    private ConcurrentHashMap<UUID, Connection> busyConnections;
    private ConcurrentLinkedDeque<Connection> idleConnections;

    private AtomicBoolean closing;

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
            this.open().close(); //Initialize JOOQ within constructor

        } catch (Exception e) {
            /*Log*/
            //TODO: Answer All requests with database error page <- use jersey filter
        }
    }

    public class DbException extends Exception{
        public DbException(){
            super("DB Shutting Down");
        }
    }

    public class Db implements AutoCloseable{
        private Connection connection;
        private Configuration configuration;
        private UUID id;

        public Db(Connection connection, UUID id){
            this.connection = connection;
            this.id = id;
            this.configuration = new DefaultConfiguration().set(this.connection).set(SQLDialect.MYSQL);
        }

        public UUID getId() {
            return id;
        }

        public void close() throws Exception{
            if(!this.connection.isClosed() && !closing.get()){
                    idleConnections.addFirst(busyConnections.remove(this.id));
                }
        }

        public Connection getConnection() {
            return connection;
        }

        public Configuration getConfiguration() {
            return configuration;
        }
    }

    public Db open() throws SQLException, DbException {
        if(this.closing.get()){
            throw new DbException();
        }

        Connection reserved = this.idleConnections.pollLast();
        UUID id = UUID.randomUUID();

        if(reserved == null || reserved.isClosed()){
            reserved = DriverManager.getConnection(this.url, this.username, this.password);
        }

        this.busyConnections.put(id, reserved);

        return new Db(reserved, id);
    }

    public void close(UUID id) throws SQLException {
        Connection idle = busyConnections.remove(id);

        if(!idle.isClosed()){
            idleConnections.addFirst(idle);
        }
    }

    public void closeAll(){
        if(closing.compareAndSet(false, true)){
            for(Connection connection : this.busyConnections.values()){
                try{
                    if(!connection.isClosed()){
                        connection.close();
                    }
                }catch (SQLException e){/*Log*/}
            }

            for (Connection connection : this.idleConnections) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {/*Log*/}
            }
        }
    }
}
