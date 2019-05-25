package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Order;
import by.epam.javatr.minchuk.project.model.entity.Vaucher;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.OrderService;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import by.epam.javatr.minchuk.project.service.VaucherService;
import by.epam.javatr.minchuk.project.service.impl.VaucherServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class {@code ViewAllVauchers}
 *
 * @author Oksana Minchuk
 * @version 1.0 19/05/2019
 */

public class ViewAllVauchers implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start ViewAllVauchers");

        List<Entity> vauchers;
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        VaucherService vaucherService = factory.getVaucherService();
        OrderService orderService = factory.getOrderService();

        try {
            vauchers = vaucherService.findAll();
            List<Entity> orders = orderService.findAll();
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

            if (!vauchers.isEmpty()) {
                request.setAttribute("vauchers", vauchers);
                page = PageContainer.VIEW_ALL_VAUCHERS;
            } else {
                request.setAttribute("error", "Vauchers not found");
                page = PageContainer.VIEW_ALL_VAUCHERS;
            }

        } catch (TravelAgencyServiceException e) {
            LOGGER.error("ViewAllVauchers error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish ViewAllVauchers");
        return page;
    }
}
