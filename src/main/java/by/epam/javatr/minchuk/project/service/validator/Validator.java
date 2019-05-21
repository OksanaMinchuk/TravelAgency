package by.epam.javatr.minchuk.project.service.validator;

import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import by.epam.javatr.minchuk.project.service.UserService;
import by.epam.javatr.minchuk.project.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Class {@code Validator} validates user input data.
 *
 * @author Oksana Minchuk
 * @version 1.0 09/05/2019
 */

public class Validator {

    private static final String NANE_AND_SURNAME_PATTERN = "[А-Яа-яA-Za-z]{1,15}";
    private static final String LOGIN_AND_PASSWORD_PATTERN = "[A-Za-z\\d]{1,10}";
    private static final String EMAIL_PATTERN = "([A-Za-z\\d]+)@([A-Za-z]+)\\.[A-Za-z]{1,5}";
    private static final String DATE_PATTERN = "[2][\\d]{3}\\-[0-1][0-9]\\-[0-3][0-9]";

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    private static final Validator instance = new Validator();

    public Validator() {
    }

    public static Validator getInstance() {
        return instance;
    }

    /**
     * Validate name or surname
     * @param string
     * @return
     */
    public boolean validateNameOrSurname (String string) {
        return string.matches(NANE_AND_SURNAME_PATTERN);
    }

    public boolean validateMoney (Double money) {
        return money >= 0;
    }

    /**
     * Validate login or password
     * @param string
     * @return
     */
    public boolean validateLoginOrPassword (String string) {
        return string.matches(LOGIN_AND_PASSWORD_PATTERN);
    }

    public boolean validateEmail (String string) {
        return string.matches(EMAIL_PATTERN);
    }

    public boolean validateDate (String string) {
        return string.matches(DATE_PATTERN);
    }

    public boolean validateUniqeLogin (String login) throws TravelAgencyServiceException {
        LOGGER.debug("start validateUniqeLogin login: " + login);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        List<Entity> users = userService.findAll();
        int count = 0;
        int usersCount = users.size();
        for (int i = 0; i < usersCount; i++) {
            User user = (User) users.get(i);

            if (user.getLogin().equals(login)) {
                count++;
            }
        }
        LOGGER.debug("finish validateUniqeLogin, find users: " + count);
        return count == 0;
    }

}
