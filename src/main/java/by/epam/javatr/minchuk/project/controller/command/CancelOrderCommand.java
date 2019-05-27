package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.entity.Order;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.OrderService;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code CancelOrderCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 25/05/2019
 */

public class CancelOrderCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start CancelOrderCommand");

        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        OrderService orderService = factory.getOrderService();

        String orderID = request.getParameter("myOrderId");
        LOGGER.debug("orderID: " + orderID);

        try {
            Order order = (Order) orderService.findById(Integer.parseInt(orderID));
            LOGGER.debug("cancel order: " + order);
            if (order != null) {
                orderService.cancelOrder(order);
            } else {
                request.setAttribute("errorUserMenu", "Order not found.");
            }
            page = PageContainer.USER_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            LOGGER.error("CancelOrderCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        } catch (NumberFormatException e) {
            LOGGER.error("CancelOrderCommand error.", e);
            request.setAttribute("error", "Select order to cancel");
            page = PageContainer.ERROR_PAGE;
        }

        LOGGER.debug("finish CancelOrderCommand");
        return page;
    }
}
