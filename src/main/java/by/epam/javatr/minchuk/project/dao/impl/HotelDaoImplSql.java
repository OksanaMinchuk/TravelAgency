package by.epam.javatr.minchuk.project.dao.impl;

import by.epam.javatr.minchuk.project.dao.HotelDao;
import by.epam.javatr.minchuk.project.dao.connection.ConnectionPool;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Hotel;
import by.epam.javatr.minchuk.project.model.entity.Tour;
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
 * Class {@code HotelDaoImplSql}
 *
 * @author Oksana Minchuk
 * @version 1.0 06/05/2019
 */


public class HotelDaoImplSql implements HotelDao {

    private static final String SQL_INSERT_HOTEL = "INSERT INTO hotel (name_hotel, pricePerDay) VALUES (?,?);";
    private static final String SQL_UPDATE_HOTEL ="UPDATE hotel SET name_hotel=?, pricePerDay=? WHERE idhotel = ?;";
    private static final String SQL_DELETE_HOTEL_BY_ID = "DELETE FROM hotel WHERE idhotel = ?;";
    private static final String SQL_SELECT_HOTEL_BY_ID = "SELECT idhotel, name_hotel, pricePerDay FROM hotel WHERE idhotel = ?;";
    private static final String SQL_SELECT_ALL_HOTEL = "SELECT idhotel, name_hotel, pricePerDay FROM hotel;";

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public void create(Entity entity) throws TravelAgencyDAOException {
        LOGGER.debug("start hotel registration");

        if (entity instanceof Hotel) {
            Hotel hotel =(Hotel) entity;
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.takeConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_INSERT_HOTEL);
                ps.setString(1, hotel.getName());
                ps.setDouble(2, hotel.getPricePerDay());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                LOGGER.error("hotel registration exception ", e);
                throw new TravelAgencyDAOException("hotel registration exception", e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        LOGGER.error("database access error occurs", e);
                        throw new TravelAgencyDAOException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                LOGGER.debug("finish hotel registration");
            }
        } else {
            throw new TravelAgencyDAOException("entity in method parameter is not instance of hotel");
        }
    }

    @Override
    public void update(Entity entity) throws TravelAgencyDAOException {
        LOGGER.debug("start update hotel by ID");

        if (entity instanceof Hotel) {
            Hotel hotel =(Hotel) entity;
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.takeConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_UPDATE_HOTEL);
                ps.setString(1, hotel.getName());
                ps.setDouble(2, hotel.getPricePerDay());
                ps.setInt(3, hotel.getId());
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                LOGGER.error("hotel update exception ", e);
                throw new TravelAgencyDAOException("hotel update exception", e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        LOGGER.error("database access error occurs", e);
                        throw new TravelAgencyDAOException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                LOGGER.debug("finish update hotel by ID");
            }
        } else {
            throw new TravelAgencyDAOException("entity in method parameter is not instance of hotel");
        }

    }

    @Override
    public void delete(int id) throws TravelAgencyDAOException {
        LOGGER.debug("start delete hotel by ID");

        if(id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.takeConnection();
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(SQL_DELETE_HOTEL_BY_ID);
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
            } catch (TravelAgencyConnectionPoolException | SQLException e) {
                LOGGER.error("hotel delete by ID exception ", e);
                throw new TravelAgencyDAOException("hotel delete by ID exception", e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        LOGGER.error("database access error occurs", e);
                        throw new TravelAgencyDAOException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                LOGGER.debug("finish delete hotel by ID");
            }
        }
    }

    @Override
    public Entity findById(int id) throws TravelAgencyDAOException {
        LOGGER.debug("start find hotel by ID");
        Hotel hotel = null;

        if (id > 0) {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection = null;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                connection = connectionPool.takeConnection();
                ps = connection.prepareStatement(SQL_SELECT_HOTEL_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    hotel = new Hotel();
                    hotel.setId(rs.getInt(1));
                    hotel.setName(rs.getString(2));
                    hotel.setPricePerDay(rs.getDouble(3));
                }
            } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
                LOGGER.error("hotel find by ID exception ", e);
                throw new TravelAgencyDAOException("hotel find by ID exception", e);
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        LOGGER.error("database access error occurs", e);
                        throw new TravelAgencyDAOException("database access error occurs", e);
                    }
                }
                if (connectionPool != null) {
                    connectionPool.releaseConnection(connection);
                }
                LOGGER.debug("finish find hotel by ID");
            }
        }
        return hotel;
    }

    @Override
    public List<Entity> findAll() throws TravelAgencyDAOException {
        LOGGER.debug("start find all hotels");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Hotel hotel = null;
        List<Entity> hotels= new ArrayList<>();;
        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(SQL_SELECT_ALL_HOTEL);
            rs = ps.executeQuery();
            while (rs.next()) {
                hotel = new Hotel();
                hotel.setId(rs.getInt(1));
                hotel.setName(rs.getString(2));
                hotel.setPricePerDay(rs.getDouble(3));
                hotels.add(hotel);
            }
        } catch (TravelAgencyConnectionPoolException | SQLException | TravelAgencyDataWrongException e) {
            LOGGER.error("find all hotels exception ", e);
            throw new TravelAgencyDAOException("find all hotels exception", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("database access error occurs", e);
                    throw new TravelAgencyDAOException("database access error occurs", e);
                }
            }
            if (connectionPool != null) {
                connectionPool.releaseConnection(connection);
            }
            LOGGER.debug("finish find all hotels");
        }
        return hotels;
    }

}
