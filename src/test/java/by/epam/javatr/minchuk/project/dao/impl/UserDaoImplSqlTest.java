package by.epam.javatr.minchuk.project.dao.impl;

import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.type.RoleType;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class UserDaoImplSqlTest {

    private Connection connection;
    private UserDaoImplSql userDao = new UserDaoImplSql();
    private User user1 = new User(1, "TestName1", "TestSurname1", 0, 1000,
            "email1@google.com", "login1", "pass1", RoleType.getValue(1));
    private User user2 = new User(2, "TestName2", "TestSurname2", 0, 2000,
            "email2@google.com", "login2", "pass2", RoleType.getValue(1));
    private User user3 =  new User(3, "TestName3", "TestSurname3", 0, 0,
            "email3@google.com", "login3", "pass3", RoleType.getValue(1));
    private User user4;


    @Test
    public void testLogIn() {
        User actualUser = userDao.logIn("login1", "pass1");
        assertEquals(actualUser, user1);
    }

    @Test(groups = "databaseChangeTest")
    public void testSetDiscount() {
        userDao.setDiscount(3, 7);
    }


    @Test(groups = "databaseChangeTest")
    public void testCreate() throws TravelAgencyDAOException {
        user4 = new User(4, "TestName4", "TestSurname4", 0, 4000,
                "email4@google.com", "login4", "pass4", RoleType.getValue(1));
        userDao.create(user4);
    }

    @Test(groups = "databaseChangeTest")
    public void testUpdate() throws TravelAgencyDAOException, TravelAgencyDataWrongException {
        user3.setName("Update3");
        userDao.update(user3);
        User actual = (User) userDao.findById(user3.getId());
        assertEquals(actual, user3);
    }

    @Test(groups = "databaseChangeTest", enabled = false)
    public void testDelete() {
        userDao.delete(23);
    }

    @Test
    public void testFindById() {
        User expUser = user2;
        User actualUser = (User) userDao.findById(2);
        assertEquals(actualUser, expUser);
    }

    @Test(enabled = false)
    public void testFindAll() {
        List<Entity> expUsers = new ArrayList<>();
        expUsers.add(user1);
        expUsers.add(user2);
        expUsers.add(user3);

        List<Entity> actUsers = userDao.findAll();
        assertEquals(actUsers, expUsers);
    }
}