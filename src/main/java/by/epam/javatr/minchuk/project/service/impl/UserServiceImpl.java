package by.epam.javatr.minchuk.project.service.impl;

import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User logIn(String login, String password) {
        return null;
    }

    @Override
    public void setDiscount(int id, double discount) {

    }

    @Override
    public void setMoney(int id, double money) {

    }

    @Override
    public void create(Entity entity) throws TravelAgencyDAOException {

    }

    @Override
    public void update(Entity entity) throws TravelAgencyDAOException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Entity findById(int id) {
        return null;
    }

    @Override
    public List<Entity> findAll() {
        return null;
    }
}
