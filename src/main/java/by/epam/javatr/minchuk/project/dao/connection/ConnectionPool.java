package by.epam.javatr.minchuk.project.dao.connection;

import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class {@code ConnectionPool}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public class ConnectionPool {

    private volatile static ConnectionPool instance;
    private static final Logger LOGGER;
    private static AtomicBoolean create = new AtomicBoolean(false);

    private BlockingQueue<ProxyConnection> avaialableConnections;
    private BlockingQueue<ProxyConnection> givenConnections;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    static {
        LOGGER = Logger.getRootLogger();
    }

    private ConnectionPool() {
        this.driverName = DatabaseManager.getValue(DatabaseManager.JDBS_MYSQL_DRIVER);
        this.url = DatabaseManager.getValue(DatabaseManager.DATABASE_URL);
        this.user = DatabaseManager.getValue(DatabaseManager.DATABASE_USER);
        this.password = DatabaseManager.getValue(DatabaseManager.DATABASE_PASSWORD);
        this.poolSize = Integer.parseInt(DatabaseManager.getValue(DatabaseManager.CONNECTION_COUNT));

        try {
            init();
        } catch (TravelAgencyConnectionPoolException e) {
            LOGGER.error("Connection initialization exception");
        }
    }

    /**
     *
     * @return singleton ConnectionPool object
     */
    public static ConnectionPool getInstance() {
        if (!create.get()) {
            if (instance == null) {
                synchronized (ConnectionPool.class) {
                    if (instance == null) {
                        instance = new ConnectionPool();
                        create.set(true);
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Create initialization of connection pool
     *
     * @throws TravelAgencyConnectionPoolException
     */
    private void init() throws TravelAgencyConnectionPoolException {

        LOGGER.debug("init PoolConnection start");

        try {
            Class.forName(driverName);
            avaialableConnections = new ArrayBlockingQueue<>(poolSize);
            givenConnections = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                avaialableConnections.add(proxyConnection);
            }

        } catch (SQLException e) {
            LOGGER.error("Connection initialization SQLException");
            throw new TravelAgencyConnectionPoolException("Connection initialization SQLException", e);

        } catch (ClassNotFoundException e) {
            LOGGER.error("Connection initialization ClassNotFoundException");
            throw new TravelAgencyConnectionPoolException("Connection initialization ClassNotFoundException", e);
        }
        LOGGER.debug("init PoolConnection finish");
    }

    /**
     * Get connection from connection pool
     *
     * @return connection from connection pool
     *
     * @throws TravelAgencyConnectionPoolException
     */
    public Connection takeConnection() throws TravelAgencyConnectionPoolException {

        LOGGER.debug("takeConnection start");

        ProxyConnection connection = null;
        try {
            connection = avaialableConnections.take();
            givenConnections.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted while waiting connection taking");
            throw new TravelAgencyConnectionPoolException("Interrupted while waiting connection taking", e);
        }

        LOGGER.debug("takeConnection finish");
        return connection;
    }

    /**
     * Return connection to connection pool
     *
     * @param connection
     */
    public void releaseConnection(Connection connection) {

        LOGGER.debug("releaseConnection start");

        if (connection instanceof ProxyConnection) {
            try {
                connection.setAutoCommit(true);
                givenConnections.remove(connection);
                avaialableConnections.add((ProxyConnection) connection);
            } catch (SQLException e) {
                LOGGER.error("Database access error");
            }
        }
        LOGGER.debug("releaseConnection finish");
    }

    /**
     * Close all connections
     */
    public void closePoolConnection() {

        LOGGER.debug("closePoolConnection start");

        int poolSize = avaialableConnections.size();

        for (int i = 0; i < poolSize; i++) {
            try {
                avaialableConnections.take().realClose();
            } catch (SQLException e) {
                LOGGER.error("Database access error");
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted while waiting connection taking");
            }
        }
        LOGGER.debug("closePoolConnection finish");
    }
}
