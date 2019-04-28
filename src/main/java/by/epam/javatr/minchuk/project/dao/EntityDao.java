package by.epam.javatr.minchuk.project.dao;

import by.epam.javatr.minchuk.project.model.entity.Entity;

import java.util.List;

/**
 * Interface {@code EntityDao}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public interface EntityDao {

    public void create(Entity entity);
    public Entity update (Entity entity);
    public void delete (int id);
    public void delete (Entity entity);
    public Entity findAllById (int id);
    public List<Entity> findAll ();

}
