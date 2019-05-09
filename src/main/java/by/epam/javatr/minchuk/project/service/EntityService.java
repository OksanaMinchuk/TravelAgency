package by.epam.javatr.minchuk.project.service;

import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;

import java.util.List;

/**
 * Interface {@code EntityService}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public interface EntityService {

    void create(Entity entity) throws TravelAgencyDAOException;
    void update (Entity entity) throws TravelAgencyDAOException;
    void delete (int id);
    Entity findById (int id);
    List<Entity> findAll ();
}
