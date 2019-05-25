package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyCommandException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import by.epam.javatr.minchuk.project.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class {@code UpdateBalanceCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 25/05/2019
 */

public class UpdateBalanceCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start UpdateBalanceCommand");

        String page;
        HttpSession session = request.getSession(true);
        User currentUser = (User) request.getSession().getAttribute("user");
        int userID = currentUser.getId();

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        Double newBalance;

        try {
            try {
                newBalance = Double.parseDouble(request.getParameter("newBalance"));
                session.setAttribute("usermoney", newBalance);

            } catch (NumberFormatException e) {
                LOGGER.error("TravelAgencyCommandException error.", e);
                throw new TravelAgencyCommandException();
            }

            if (newBalance >= 0) {
                userService.setMoney(userID, newBalance);
                session.setAttribute("acceptedMessage", "accepted");
                page = PageContainer.ACCOUNT_PAGE;
            } else {
                LOGGER.error("Incorrect balace value.");
                session.setAttribute("error", "Incorrect balance value.");
                page = PageContainer.ERROR_PAGE;
            }
        } catch (TravelAgencyServiceException | TravelAgencyCommandException e) {
            LOGGER.error("TravelAgencyServiceException error.", e);
            session.setAttribute("error", "Incorrect balance value.");
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish UpdateBalanceCommand");
        return page;
    }
}
