package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
import by.epam.javatr.minchuk.project.service.TourService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class {@code UpdateTourCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 25/05/2019
 */

public class UpdateTourCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start UpdateTourCommand");
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        TourService tourService = factory.getTourService();

        try {
            int idTour = Integer.parseInt(request.getParameter("idTour"));
            LOGGER.debug("request.getParameter(\"idTour\")" + request.getParameter("idTour"));
            LOGGER.debug(idTour);
            boolean isHot = Boolean.parseBoolean(request.getParameter("isHot"));
            LOGGER.debug(isHot);
            tourService.setHotTour(idTour, isHot);

            request.setAttribute("acceptedMessageAdminUpdate", "update tour accepted");
            page = PageContainer.ADMIN_MENU_PAGE;

        } catch (TravelAgencyServiceException e) {
            LOGGER.error("AddVaucherCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        }
        LOGGER.debug("finish UpdateTourCommand");
        return page;
    }
}
