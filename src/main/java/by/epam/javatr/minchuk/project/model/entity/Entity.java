package by.epam.javatr.minchuk.project.model.entity;

import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class {@code Entity} is a superclass of all entities.
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public abstract class Entity implements Serializable, Cloneable {

    private int id;

    public Entity() {
    }

    public Entity(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws TravelAgencyDataWrongException {
        if (id > 0) {
            this.id = id;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect id value.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id=" + id + ", ";
    }
}
