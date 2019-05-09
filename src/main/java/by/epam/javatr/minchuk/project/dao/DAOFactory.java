package by.epam.javatr.minchuk.project.dao;

import by.epam.javatr.minchuk.project.dao.impl.*;
import org.apache.log4j.Logger;

/**
 * Class {@code DAOFactory} offers to get object references,
 * which class implements the required DAO interface
 *
 * @author Oksana Minchuk
 * @version 1.0 06/05/2019
 */

public class DAOFactory {

    private static final UserDao USER_DAO = new UserDaoImplSql();
    private static final VaucherDao VAUCHER_DAO = new VaucherDaoImplSql();
    private static final TourDao TOUR_DAO = new TourDaoImplSql();
    private static final HotelDao HOTEL_DAO = new HotelDaoImplSql();
    private static final OrderDao ORDER_DAO = new OrderDaoImplSql();

    private static final DAOFactory instance = new DAOFactory();
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    private DAOFactory() {
        LOGGER.debug("create DAO factory");
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return USER_DAO;
    }

    public VaucherDao getVaucherDao() {
        return VAUCHER_DAO;
    }

    public TourDao getTourDao() {
        return TOUR_DAO;
    }

    public HotelDao getHotelDao() {
        return HOTEL_DAO;
    }

    public OrderDao getOrderDao() {
        return ORDER_DAO;
    }
}
