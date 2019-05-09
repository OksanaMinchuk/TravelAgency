package by.epam.javatr.minchuk.project.service;

import by.epam.javatr.minchuk.project.model.entity.User;

/**
 * Interface {@code UserService}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public interface UserService extends EntityService {

    User logIn(String login, String password);
    void setDiscount(int id, double discount);
    void setMoney(int id, double money);

}
