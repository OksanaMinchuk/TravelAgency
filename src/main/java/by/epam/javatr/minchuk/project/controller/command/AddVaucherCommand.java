package by.epam.javatr.minchuk.project.controller.command;

import by.epam.javatr.minchuk.project.controller.Command;
import by.epam.javatr.minchuk.project.controller.PageContainer;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Hotel;
import by.epam.javatr.minchuk.project.model.entity.Tour;
import by.epam.javatr.minchuk.project.model.entity.Vaucher;
import by.epam.javatr.minchuk.project.model.entity.type.TransportType;
import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Class {@code AddVaucherCommand}
 *
 * @author Oksana Minchuk
 * @version 1.0 25/05/2019
 */

public class AddVaucherCommand implements Command {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getRootLogger();
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER.debug("start AddVaucherCommand");
        String page = null;

        ServiceFactory factory = ServiceFactory.getInstance();
        TourService tourService = factory.getTourService();
        HotelService hotelService = factory.getHotelService();
        VaucherService vaucherService = factory.getVaucherService();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            List<Entity> tours = tourService.findAll();
            List<Entity> hotels = hotelService.findAll();
            request.setAttribute("tours", tours);
            LOGGER.debug("tours " + tours);
            request.setAttribute("hotels", hotels);
            LOGGER.debug("tours " + tours);

            String country = request.getParameter("countryVal");
            String dateFrom = request.getParameter("dateFromVal");
            String dateTo = request.getParameter("dateToVal");

            int idTour = Integer.parseInt(request.getParameter("idTour"));
            int idTransport = Integer.parseInt(request.getParameter("idTransport"));
            int idHotel = Integer.parseInt(request.getParameter("idHotel"));

            Vaucher vaucher = new Vaucher();
            vaucher.setCountry(country);
            vaucher.setDateFrom(dateFormat.parse(dateFrom));
            vaucher.setDateTo(dateFormat.parse(dateTo));
            vaucher.setTour((Tour) tourService.findById(idTour));
            vaucher.setTransport(TransportType.getValue(idTransport));
            vaucher.setHotel((Hotel) hotelService.findById(idHotel));

            LOGGER.debug("add vaucher " + vaucher);

            vaucherService.create(vaucher);
            request.setAttribute("acceptedMessageAdminAdd", "add vaucher accepted");
            page = PageContainer.ADMIN_MENU_PAGE;

        } catch (TravelAgencyServiceException | TravelAgencyDAOException | TravelAgencyDataWrongException e) {
            LOGGER.error("AddVaucherCommand error.", e);
            page = PageContainer.ERROR_PAGE;
        } catch (ParseException | NumberFormatException e) {
            LOGGER.error("AddVaucherCommand error.", e);
            request.setAttribute("error", "Incorrect data value. Please try again.");
            page = PageContainer.ERROR_PAGE;
        }

        LOGGER.debug("finish AddVaucherCommand");
        return page;
    }
}
