package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Order;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.entity.Vaucher;
import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.OrderService;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import by.epam.javatr.minchuk.project.service.UserService;
import by.epam.javatr.minchuk.project.service.VaucherService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class {@code BookVaucherCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 25/05/2019
 */

public class BookVaucherCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start BookVaucherCommand");

        String page;

        ServiceFactory factory = ServiceFactory.getInstance();
        OrderService orderService = factory.getOrderService();
        VaucherService vaucherService = factory.getVaucherService();
        UserService userService = factory.getUserService();

        String vaucherID = request.getParameter("idvaucher");
        LOGGER.debug("vaucherID: " + vaucherID);
        String userID = request.getParameter("id");
        LOGGER.debug("userID: " + userID);
        List<Entity> vauchers;

        Order order = null;

        try {
            vauchers = vaucherService.findAll();

            Vaucher vaucher = (Vaucher) vaucherService.findById(Integer.parseInt(vaucherID));
            LOGGER.debug("vaucher: " + vaucher);
            User user = (User) userService.findById(Integer.parseInt(userID));
            LOGGER.debug("user: " + user);

            order = new Order();
            double totalPrice = calculateTotalPrice(vaucher, user);

            if (user.getMoney() >= totalPrice) {
                try {
                    order.setUser(user);
                    order.setVaucher(vaucher);
                } catch (TravelAgencyDataWrongException e) {
                    e.printStackTrace();
                }
                orderService.create(order);

                LOGGER.debug("order: " + order);

                request.setAttribute("vauchers", vauchers);

                request.setAttribute("userName", user.getName());
                request.setAttribute("countryOrder", order.getVaucher().getCountry());
                request.setAttribute("totalPrice", totalPrice);

                page = PageContainer.FINISH_ORDER;

            } else {
                request.setAttribute("notEnouthMoneyMessage", "Sorry, not enough money to pay.");
                page = PageContainer.USER_MENU_PAGE;
            }

        } catch (TravelAgencyDAOException e) {
            LOGGER.error("BookVaucherCommand error.", e);
            page = PageContainer.ERROR_PAGE;

        } catch (TravelAgencyServiceException e) {
            LOGGER.error("BookVaucherCommand error.", e);
            page = PageContainer.ERROR_PAGE;

        } catch (NumberFormatException e) {
            LOGGER.error("BookVaucherCommand error.", e);
            request.setAttribute("error", "Create order, please.");
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish BookVaucherCommand: " + order);
        return page;
    }

    /**
     *
     * @param vaucher
     * @param user
     * @return
     */
    private double calculateTotalPrice(Vaucher vaucher, User user) {
        int nights = (int)(vaucher.getDateTo().getTime() - vaucher.getDateFrom().getTime())/(24 * 60 * 60 * 1000);
        double totalPrice = (nights * vaucher.getHotel().getPricePerDay() + vaucher.getTour().getPrice())*(100 - user.getDiscount())/100;
        return totalPrice;
    }
}
