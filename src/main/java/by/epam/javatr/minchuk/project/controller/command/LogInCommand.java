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
 * Class {@code LogInCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class LogInCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start LogInCommand");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        HttpSession session = request.getSession(true);

        try {
            User user = userService.logIn(login, password);

            if (user != null) {
                request.setAttribute("user", user);
                session.setAttribute("id", user.getId());
                session.setAttribute("name", user.getName());
                session.setAttribute("surname", user.getSurname());
                session.setAttribute("money", user.getMoney());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("userRole", user.getRole());

                if (user.getRole().toString().equals("CLIENT")) {
                    page = PageContainer.USER_MENU_PAGE;
                } else if (user.getRole().toString().equals("ADMIN")) {
                    page = PageContainer.ADMIN_MENU_PAGE;
                }
            } else {
                session.setAttribute("error", "User not found. Create your account, please.");
                page = PageContainer.ERROR_PAGE;
            }
        } catch (TravelAgencyServiceException e) {
            LOGGER.error("LogInCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish LogInCommand");
        return page;
    }
}
