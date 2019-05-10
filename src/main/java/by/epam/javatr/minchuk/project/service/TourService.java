package by.epam.javatr.minchuk.project.service;

import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;

/**
 * Interface {@code TourService}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public interface TourService extends EntityService {

    void setHotTour(int id, boolean isHot) throws TravelAgencyServiceException;
}
