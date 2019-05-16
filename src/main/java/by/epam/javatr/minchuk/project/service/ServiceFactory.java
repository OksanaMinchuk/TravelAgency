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

    private final UserService USER_SERVICE = new UserServiceImpl();
    private final TourService TOUR_SERVICE = new TourServiceImpl();
    private final HotelService HOTEL_SERVICE = new HotelServiceImpl();
    private final VaucherService VAUCHER_SERVICE = new VaucherServiceImpl();
    private final OrderService ORDER_SERVICE = new OrderServiceImpl();

    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
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


