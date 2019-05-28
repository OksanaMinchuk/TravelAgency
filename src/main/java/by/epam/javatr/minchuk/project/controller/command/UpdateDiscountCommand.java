package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import by.epam.javatr.minchuk.project.service.TourService;
import by.epam.javatr.minchuk.project.service.UserService;
import by.epam.javatr.minchuk.project.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code UpdateDiscountCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 25/05/2019
 */

public class UpdateDiscountCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start UpdateDiscountCommand");
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        try {
           // LOGGER.debug(request.getParameter("discountVal"));
            int idUser = Integer.parseInt(request.getParameter("idUser"));
            LOGGER.debug(idUser);
            double discount = Double.parseDouble(request.getParameter("discountVal"));


            LOGGER.debug(discount);
            userService.setDiscount(idUser, discount);

            request.setAttribute("acceptedMessageAdminDiscount", "update discount accepted");
            page = PageContainer.ADMIN_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            LOGGER.error("UpdateDiscountCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish UpdateDiscountCommand");
        return page;
    }

}
