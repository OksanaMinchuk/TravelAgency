package by.epam.javatr.minchuk.project.dao;

import by.epam.javatr.minchuk.project.model.entity.User;

/**
 * Interface {@code UserDao}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public interface UserDao {

    public void logIn(String login, String password);
    public void logOut(User user); ///////////////////
    public void registration(User user);

}
