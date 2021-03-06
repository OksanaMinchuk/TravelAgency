package by.epam.javatr.minchuk.project.service;

import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Order;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;

import java.util.List;

/**
 * Interface {@code OrderService}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public interface OrderService extends EntityService {

    void cancelOrder (Order order) throws TravelAgencyServiceException;
    List<Entity> findByUserId (int userID) throws TravelAgencyServiceException;
}
