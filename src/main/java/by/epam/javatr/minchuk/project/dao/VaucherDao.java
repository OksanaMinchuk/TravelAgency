package by.epam.javatr.minchuk.project.dao;

import by.epam.javatr.minchuk.project.model.entity.Vaucher;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;

import java.util.List;

/**
 * Interface {@code VaucherDao}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public interface VaucherDao extends EntityDao {

    List<Vaucher> getVauchersByCountry(String country) throws TravelAgencyDAOException;
    List<Vaucher> getVauchersByTourType(String type) throws TravelAgencyDAOException;
}
