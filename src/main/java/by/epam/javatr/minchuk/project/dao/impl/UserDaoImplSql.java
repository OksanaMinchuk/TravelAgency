package by.epam.javatr.minchuk.project.dao.impl;

import by.epam.javatr.minchuk.project.dao.UserDao;
import by.epam.javatr.minchuk.project.dao.connection.ConnectionPool;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.type.RoleType;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyConnectionPoolException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code UserDaoImplSql}
 *
 * @author Oksana Minchuk
 * @version 1.0 06/05/2019
 */

public class UserDaoImplSql implements UserDao {

    private static final String SQL_INSERT_USER
            = "INSERT INTO user (name, surname, email, login, password, role_idrole) VALUES (?,?,?,?,?,?);";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE iduser = ?;";
    private static final String SQL_SELECT_USER_BY_ID
            = "SELECT iduser, name, surname, discount, email, login, password, role_idrole FROM user WHERE iduser = ?;";
    private static final String SQL_FIND_ALL_USER
            = "SELECT iduser, name, surname, discount, email, login, password, role_idrole FROM user";
    private static final String SQL_UPDATE_USER_BY_ID
            = "UPDATE user SET name=?, surname=?, discount=?, email=?, login=?, password=?, role_idrole=? WHERE iduser=?;";
    private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD
            = "SELECT iduser, name, surname, discount, email, login, password, role_idrole FROM user WHERE login = ? AND password=?;";
    private static final String SQL_UPDATE_USER_DISCOUNT_BY_ID
            = "UPDATE user SET discount=? WHERE iduser=?;";

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public User logIn(String login, String password) {
        LOGGER.debug("start user logIn");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setDiscount(rs.getDouble(4));
                user.setEmail(rs.getString(5));
                user.setLogin(rs.getString(6));
                user.setPassword(rs.getString(7));
                user.setRole(RoleType.getValue(rs.getInt(8)));
            }
        } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
            LOGGER.error("user logIn exception ", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
            LOGGER.debug("finish user registration");
        }
        return user;
    }

    @Override
    public void setDiscount(int id, double discount) {
        LOGGER.debug("start setDiscount user by ID");
        if (id >= 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.takeConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_UPDATE_USER_DISCOUNT_BY_ID);
                ps.setDouble(1, discount);
                ps.setInt(2, id);
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                LOGGER.error("user setDiscount exception ", e);
            } finally {
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                LOGGER.debug("finish setDiscount user by ID");
            }
        }
    }

    /**
     * Add user to the database
     *
     * @param entity
     * @throws TravelAgencyDAOException
     */
    @Override
    public void create(Entity entity) throws TravelAgencyDAOException {
        LOGGER.debug("start user registration");
        if (entity instanceof User) {
            User user = (User) entity;
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.takeConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_INSERT_USER);
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getLogin());
                ps.setString(5, user.getPassword());
                ps.setInt(6, user.getRole().getId());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                LOGGER.error("user registration exception ", e);
            } finally {
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                LOGGER.debug("finish user registration");
            }
        } else {
            throw new TravelAgencyDAOException("entity in method parameter is not instance of user");
        }
    }

    @Override
    public void update(Entity entity) throws TravelAgencyDAOException {
        LOGGER.debug("start update user by ID");
        if (entity instanceof User) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            User user = (User) entity;
            try {
                connection = connectionPool.takeConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);
                ps.setString(1, user.getName());
                ps.setString(2, user.getSurname());
                ps.setDouble(3, user.getDiscount());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getLogin());
                ps.setString(6, user.getPassword());
                ps.setInt(7, user.getRole().getId());
                ps.setInt(8, user.getId());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                LOGGER.error("user update exception ", e);
            } finally {
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                LOGGER.debug("finish update user by ID");
            }
        } else {
            throw new TravelAgencyDAOException("entity in method parameter is not instance of user");
        }
    }

    @Override
    public void delete(int id) {
        LOGGER.debug("start delete user by ID");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (TravelAgencyConnectionPoolException | SQLException e) {
            LOGGER.error("user delete by ID exception ", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
            LOGGER.debug("finish delete user by ID");
        }

    }

    @Override
    public Entity findById(int id) {
        LOGGER.debug("start find user by ID");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User user = null;
        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setDiscount(rs.getInt(4));
                user.setEmail(rs.getString(5));
                user.setLogin(rs.getString(6));
                user.setPassword(rs.getString(7));
                user.setRole(RoleType.getValue(rs.getInt(8)));
            }
        } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
            LOGGER.error("user find by ID exception ", e);
        } finally {
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
            LOGGER.debug("finish find user by ID");
        }
        return user;
    }

    @Override
    public List<Entity> findAll() {
        LOGGER.debug("start find all users");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User user = null;
        List<Entity> users= new ArrayList<>();;
        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SQL_FIND_ALL_USER);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setDiscount(rs.getInt(4));
                user.setEmail(rs.getString(5));
                user.setLogin(rs.getString(6));
                user.setPassword(rs.getString(7));
                user.setRole(RoleType.getValue(rs.getInt(8)));
                users.add(user);
            }
        } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
            e.printStackTrace();
        } finally {
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
            LOGGER.debug("finish find all users");
        }
        return users;
    }


}
