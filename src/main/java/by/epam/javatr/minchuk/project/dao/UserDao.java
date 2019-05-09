package by.epam.javatr.minchuk.project.dao;

import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;

/**
 * Interface {@code UserDao}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public interface UserDao extends EntityDao {

    User logIn(String login, String password);
    void setDiscount(int id, double discount);
    void setMoney(int id, double money);

}
