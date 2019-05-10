package by.epam.javatr.minchuk.project.service.impl;

import by.epam.javatr.minchuk.project.dao.DAOFactory;
import by.epam.javatr.minchuk.project.dao.UserDao;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.UserService;

import java.util.List;

/**
 * Class {@code UserServiceImpl}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class UserServiceImpl implements UserService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDao userDao = daoFactory.getUserDao();

    public UserServiceImpl() {
    }

    @Override
    public User logIn(String login, String password) throws TravelAgencyServiceException {
        try {
            User user = userDao.logIn(login, password);
            return  user;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void setDiscount(int id, double discount) throws TravelAgencyServiceException {
        try {
            userDao.setDiscount(id, discount);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void setMoney(int id, double money) throws TravelAgencyServiceException {
        try {
            userDao.setMoney(id, money);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void create(Entity entity) throws TravelAgencyServiceException {
        try {
            userDao.create(entity);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void update(Entity entity) throws TravelAgencyServiceException {
        try {
            userDao.update(entity);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws TravelAgencyServiceException {
        try {
            userDao.delete(id);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public Entity findById(int id) throws TravelAgencyServiceException {
        try {
            Entity user = userDao.findById(id);
            return user;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public List<Entity> findAll() throws TravelAgencyServiceException {
        try {
            List<Entity> users = userDao.findAll();
            return users;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }
}
