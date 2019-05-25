package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Order;
import by.epam.javatr.minchuk.project.model.entity.User;
import by.epam.javatr.minchuk.project.model.entity.Vaucher;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.OrderService;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import by.epam.javatr.minchuk.project.service.VaucherService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class {@code ToChooseVaucherCommand} allows to see available vauchers
 *
 * @author Oksana Minchuk
 * @version 1.0 19/05/2019
 */

public class ToChooseVaucherCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start ToChooseVaucherCommand");

        String page;

        ServiceFactory factory = ServiceFactory.getInstance();
        VaucherService vaucherService = factory.getVaucherService();
        OrderService orderService = factory.getOrderService();

        List<Entity> vauchers;
        List<Double> vaucherPrice = new ArrayList<>();

        try {
            List<Entity> orders = orderService.findAll();
            vauchers = vaucherService.findAll();
            Order order;

            //not show not available to order vauchers
            for (int i = 0; i < vauchers.size(); i++) {
                for (int j = 0; j < orders.size(); j++) {
                    order = (Order) orders.get(j);
                    if (vauchers.get(i).getId() == order.getVaucher().getId()) {
                        vauchers.remove(i);
                    }
                }
            }
            //calculate vaucher total price and create array
            for (int i = 0; i < vauchers.size(); i++) {
                vaucherPrice.add(calculateVaucherTotalPrice((Vaucher) vauchers.get(i)));
            }

            if (!vauchers.isEmpty()) {
                request.setAttribute("vauchers", vauchers);
                request.setAttribute("vaucherPrice", vaucherPrice);
                page = PageContainer.USER_MENU_PAGE;
            } else {
                request.setAttribute("error", "Vauchers not found");
                page = PageContainer.USER_MENU_PAGE;
            }
            request.setAttribute("vauchers", vauchers);
            page = PageContainer.USER_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            LOGGER.error("LogInCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }

        LOGGER.debug("start ToChooseVaucherCommand");
        return page;
    }

    private double calculateVaucherTotalPrice(Vaucher vaucher) {
        int nights = (int)(vaucher.getDateTo().getTime() - vaucher.getDateFrom().getTime())/(24 * 60 * 60 * 1000);
        double totalPrice = (nights * vaucher.getHotel().getPricePerDay() + vaucher.getTour().getPrice());
        return totalPrice;
    }
}
