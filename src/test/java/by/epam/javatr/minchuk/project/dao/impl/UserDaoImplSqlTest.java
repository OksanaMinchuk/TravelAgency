package by.epam.javatr.minchuk.project.dao.impl;

import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.type.RoleType;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class UserDaoImplSqlTest {

    private Connection connection;
    private UserDaoImplSql userDao = new UserDaoImplSql();
    private User user = new User(4, "HenryADM", "Jhonsonn", "henry@google.com",
            0, "henry", "qwerty", RoleType.getValue(2));

    @Test
    public void testLogIn() {
        User actualUser = userDao.logIn("henry", "qwerty");
        assertEquals(actualUser, user);
    }

    @Test(enabled = false)
    public void testSetDiscount() {
        userDao.setDiscount(19, 5);
    }


    @Test(enabled = false)
    public void testCreate() throws TravelAgencyDAOException {
        userDao.create( new User(6, "Jack", "Smith", "smith@google.com",
                0, "mary", "pass", RoleType.getValue(2)));
    }

    @Test(enabled = false)
    public void testUpdate() throws TravelAgencyDAOException {
        userDao.update(user);
        User actual = (User) userDao.findById(user.getId());
        assertEquals(actual, user);
    }

    @Test(enabled = false)
    public void testDelete() {
        userDao.delete(15);
    }

    @Test
    public void testFindById() {
        User actualUser = (User) userDao.findById(4);
        assertEquals(actualUser, user);
    }

    @Test
    public void testFindAll() {
        List<Entity> expUsers = new ArrayList<>();
        expUsers.add(user);
        List<Entity> actUsers = userDao.findAll();
        assertEquals(actUsers, expUsers);
    }
}