package by.epam.javatr.minchuk.project.dao;

import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Order;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;

import java.util.List;

/**
 * Interface {@code OrderDao}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public interface OrderDao extends EntityDao {

    void cancelOrder (Order order) throws TravelAgencyDAOException;
    List<Entity> findByUserId (int userId) throws TravelAgencyDAOException;

}
