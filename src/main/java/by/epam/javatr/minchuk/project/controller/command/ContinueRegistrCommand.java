package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import by.epam.javatr.minchuk.project.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class {@code ContinueRegistrCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 25/05/2019
 */

public class ContinueRegistrCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start ContinueRegistrCommand");
        String page;

        HttpSession session = request.getSession(true);
        User newUser = (User) request.getSession().getAttribute("user");

        LOGGER.debug("ContinueRegistrCommand new User: " + newUser);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        User user = null;

        try {
            user = userService.logIn(newUser.getLogin(), newUser.getPassword());
            LOGGER.debug("ContinueRegistrCommand user: " + user);
            session.setAttribute("user", user);
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            session.setAttribute("surname", user.getSurname());
            session.setAttribute("money", user.getMoney());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("userRole", user.getRole());
            page = PageContainer.USER_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            LOGGER.error("ContinueRegistrCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish ContinueRegistrCommand: " + user);
        return page;
    }
}
