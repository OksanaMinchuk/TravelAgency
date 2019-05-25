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
 * Class {@code ViewAccountCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 25/05/2019
 */

public class ViewAccountCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start ViewAccountCommand");
        String page;
        HttpSession session = request.getSession(true);
        User currentUser = (User) request.getSession().getAttribute("user");
        int userID = currentUser.getId();

        LOGGER.debug("ViewAccountCommand user: " + currentUser);

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        try {
            User user = (User) userService.findById(userID);
            if (user !=null) {
                request.setAttribute("user", user);
                session.setAttribute("username", user.getName());
                session.setAttribute("usersurname", user.getSurname());
                session.setAttribute("useremail", user.getEmail());
                session.setAttribute("usermoney", user.getMoney());
                session.setAttribute("userdiscount", user.getDiscount());
                session.setAttribute("userlogin", user.getLogin());

                page = PageContainer.ACCOUNT_PAGE;
            } else {
                LOGGER.error("User not found.");
                session.setAttribute("error", "User not found.");
                page = PageContainer.ERROR_PAGE;
            }

        } catch (TravelAgencyServiceException e) {
            LOGGER.error("ViewAccountCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish ViewAccountCommand");
        return page;
    }
}
