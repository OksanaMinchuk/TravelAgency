package by.epam.javatr.minchuk.project.service;


import by.epam.javatr.minchuk.project.model.entity.Vaucher;

import java.util.List;

/**
 * Interface {@code VaucherService}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public interface VaucherService extends EntityService {

    List<Vaucher> getVauchersByCountry(String country);
    List<Vaucher> getVauchersByTourType(String type);

}
