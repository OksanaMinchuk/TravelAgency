package by.epam.javatr.minchuk.project.dao.util;

import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class {@code ConnectionPool}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public final class ConnectionPool {

    private volatile static ConnectionPool instance;
    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenConnections;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;
    private boolean isBlocked;

    private ConnectionPool() {
        this.driverName = DBProperty.JDBS_MYSQL_DRIVER;
        this.url = DBProperty.DATABASE_URL;
        this.user = DBProperty.DATABASE_USER;
        this.password = DBProperty.DATABASE_PASSWORD;
        this.poolSize = DBProperty.CONNECTION_COUNT;
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    /**
     * Create connection with database
     *
     * @throws TravelAgencyConnectionPoolException
     */
    public void initPoolData() throws TravelAgencyConnectionPoolException {
        isBlocked = false;
        try {
            Class.forName(driverName);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            givenConnections = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection conn = DriverManager.getConnection(url, user, password);
                connectionQueue.add(conn);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new TravelAgencyConnectionPoolException(e);
        }
    }

    /**
     * Get connection from connection pool
     *
     * @return connection from connection pool
     *
     * @throws TravelAgencyConnectionPoolException
     */
    public Connection getConnection() throws TravelAgencyConnectionPoolException {
        Connection connection;
        if(this.isBlocked){
            return null;
        }
        try {
            connection = connectionQueue.take();
            givenConnections.add(connection);
        } catch (InterruptedException e) {
            throw new TravelAgencyConnectionPoolException(e);
        }
        return connection;
    }

    /**
     * Return connection to connection pool
     *
     * @param connection
     */
    public void returnConnection(Connection connection) {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        givenConnections.remove(connection);
        connectionQueue.add(connection);
    }

    /**
     * Close all connections
     *
     * @throws TravelAgencyConnectionPoolException
     * @throws InterruptedException
     */
    public void closeConnections() throws TravelAgencyConnectionPoolException, InterruptedException {
        while(givenConnections.size() != 0) {
            try {
                givenConnections.take().close();
            } catch (SQLException e) {
                throw new TravelAgencyConnectionPoolException(e);
            }
        }
        while(connectionQueue.size() != 0) {
            try {
                connectionQueue.take().close();
            } catch (SQLException e) {
                throw new TravelAgencyConnectionPoolException(e);
            }
        }
    }

}
