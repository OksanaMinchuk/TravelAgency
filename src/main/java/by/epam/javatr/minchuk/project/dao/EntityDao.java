package by.epam.javatr.minchuk.project.dao;

import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;

import java.util.List;

/**
 * Interface {@code EntityDao}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public interface EntityDao {

    void create(Entity entity) throws TravelAgencyDAOException;
    void update (Entity entity) throws TravelAgencyDAOException;
    void delete (int id) throws TravelAgencyDAOException;
    Entity findById (int id) throws TravelAgencyDAOException;
    List<Entity> findAll () throws TravelAgencyDAOException;

}
