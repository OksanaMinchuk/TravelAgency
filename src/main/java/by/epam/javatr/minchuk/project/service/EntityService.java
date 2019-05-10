package by.epam.javatr.minchuk.project.service;

import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;

import java.util.List;

/**
 * Interface {@code EntityService}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public interface EntityService {

    void create(Entity entity) throws TravelAgencyDAOException, TravelAgencyServiceException;
    void update (Entity entity) throws TravelAgencyDAOException, TravelAgencyServiceException;
    void delete (int id) throws TravelAgencyServiceException;
    Entity findById (int id) throws TravelAgencyServiceException;
    List<Entity> findAll () throws TravelAgencyServiceException;
}
