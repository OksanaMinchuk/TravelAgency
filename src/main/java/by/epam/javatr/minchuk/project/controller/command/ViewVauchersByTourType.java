package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.entity.Vaucher;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import by.epam.javatr.minchuk.project.service.VaucherService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class {@code ViewVauchersByTourType}
 *
 * @author Oksana Minchuk
 * @version 1.0 19/05/2019
 */

public class ViewVauchersByTourType implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start ViewVauchersByTourType");

        List<Vaucher> vauchers;
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        VaucherService vaucherService = factory.getVaucherService();

        String tourtype = request.getParameter("tourtype");

        try {
            vauchers = vaucherService.getVauchersByTourType(tourtype);

            if (!vauchers.isEmpty()) {
                request.setAttribute("vauchers", vauchers);
            } else {
                request.setAttribute("error", "Vauchers not found");

            }
            page = PageContainer.VIEW_ALL_VAUCHERS;
        } catch (TravelAgencyServiceException e) {
            LOGGER.error("ViewVauchersByTourType error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish ViewVauchersByTourType");
        return page;
    }
}
