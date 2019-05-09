package by.epam.javatr.minchuk.project.service.impl;

import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Vaucher;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.service.VaucherService;

import java.util.List;

public class VaucherServiceImpl implements VaucherService {

    @Override
    public List<Vaucher> getVauchersByCountry(String country) {
        return null;
    }

    @Override
    public List<Vaucher> getVauchersByTourType(String type) {
        return null;
    }

    @Override
    public void create(Entity entity) throws TravelAgencyDAOException {

    }

    @Override
    public void update(Entity entity) throws TravelAgencyDAOException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Entity findById(int id) {
        return null;
    }

    @Override
    public List<Entity> findAll() {
        return null;
    }
}
