package by.epam.javatr.minchuk.project.service.impl;

import by.epam.javatr.minchuk.project.dao.DAOFactory;
import by.epam.javatr.minchuk.project.dao.OrderDao;
import by.epam.javatr.minchuk.project.dao.VaucherDao;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Order;
import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.OrderService;

import java.util.List;

/**
 * Class {@code OrderServiceImpl}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class OrderServiceImpl implements OrderService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private OrderDao orderDao = daoFactory.getOrderDao();

    @Override
    public void cancelOrder(Order order) throws TravelAgencyServiceException {
        try {
            orderDao.cancelOrder(order);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void create(Entity entity) throws TravelAgencyServiceException {
        if (entity instanceof Order) {
            Order order = (Order) entity;
            try {
                orderDao.create(order);
            } catch (TravelAgencyDAOException  e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void update(Entity entity) {
        throw new UnsupportedOperationException("Not implemented method");
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not implemented method");
    }

    @Override
    public Entity findById(int id) throws TravelAgencyServiceException {
        if (id > 0) {
            try {
                Entity order = orderDao.findById(id);
                return order;
            } catch (TravelAgencyDAOException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public List<Entity> findAll() throws TravelAgencyServiceException {
        try {
            List<Entity> orders = orderDao.findAll();
            return orders;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public List<Entity> findByUserId(int userID) throws TravelAgencyServiceException {
        try {
            List<Entity> orders = orderDao.findByUserId(userID);
            return orders;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }
}
