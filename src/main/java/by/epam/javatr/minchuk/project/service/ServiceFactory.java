package by.epam.javatr.minchuk.project.service;

import by.epam.javatr.minchuk.project.service.impl.*;
import org.apache.log4j.Logger;

/**
 * Class {@code ServiceFactory} offers to get object references,
 * which class implements the required Service interface
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class ServiceFactory {

    private static final UserService USER_SERVICE = new UserServiceImpl();
    private static final TourService TOUR_SERVICE = new TourServiceImpl();
    private static final HotelService HOTEL_SERVICE = new HotelServiceImpl();
    private static final VaucherService VAUCHER_SERVICE = new VaucherServiceImpl();
    private static final OrderService ORDER_SERVICE = new OrderServiceImpl();

    private static final ServiceFactory instance = new ServiceFactory();
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    private ServiceFactory() {
        LOGGER.debug("create Service factory");
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public TourService getTourService() {
        return TOUR_SERVICE;
    }

    public HotelService getHotelService() {
        return HOTEL_SERVICE;
    }

    public VaucherService getVaucherService() {
        return VAUCHER_SERVICE;
    }

    public OrderService getOrderService() {
        return ORDER_SERVICE;
    }
}


