package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.OrderService;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class {@code ShowOrderByUserID}
 *
 * @author Oksana Minchuk
 * @version 1.0 06/05/2019
 */

public class ShowOrderByUserID implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start ShowOrderByUserID");

        HttpSession session = request.getSession(true);
        User user = (User) request.getSession().getAttribute("user");
        LOGGER.debug("for user: " + user);

        String page;

        ServiceFactory factory = ServiceFactory.getInstance();
        OrderService orderService = factory.getOrderService();

        try {
            List<Entity> ordersByUserId = orderService.findByUserId(user.getId());
            request.setAttribute("ordersByUserId", ordersByUserId);
            if (ordersByUserId.isEmpty()) {
                request.setAttribute("errorUserMenu", "You have no orders yet.");
            }
            page = PageContainer.USER_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            LOGGER.error("ShowOrderByUserID error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish ShowOrderByUserID");
        return page;
    }
}
