package by.epam.javatr.minchuk.project.service;


import by.epam.javatr.minchuk.project.model.entity.Vaucher;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;

import java.util.List;

/**
 * Interface {@code VaucherService}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public interface VaucherService extends EntityService {

    List<Vaucher> getVauchersByCountry(String country) throws TravelAgencyServiceException;
    List<Vaucher> getVauchersByTourType(String type) throws TravelAgencyServiceException;

}
