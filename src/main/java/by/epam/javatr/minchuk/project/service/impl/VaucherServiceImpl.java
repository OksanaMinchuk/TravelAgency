package by.epam.javatr.minchuk.project.service.impl;

import by.epam.javatr.minchuk.project.dao.DAOFactory;
import by.epam.javatr.minchuk.project.dao.VaucherDao;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Vaucher;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.VaucherService;

import java.util.List;

/**
 * Class {@code VaucherServiceImpl}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class VaucherServiceImpl implements VaucherService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private VaucherDao vaucherDao = daoFactory.getVaucherDao();

    public VaucherServiceImpl() {
    }

    @Override
    public List<Vaucher> getVauchersByCountry(String country) throws TravelAgencyServiceException {
        try {
            List<Vaucher> vauchers = vaucherDao.getVauchersByCountry(country);
            return vauchers;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public List<Vaucher> getVauchersByTourType(String type) throws TravelAgencyServiceException {
        try {
            List<Vaucher> vauchers = vaucherDao.getVauchersByCountry(type);
            return vauchers;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void create(Entity entity) throws TravelAgencyServiceException {
        try {
            vaucherDao.create(entity);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void update(Entity entity) throws TravelAgencyServiceException {
        try {
            vaucherDao.update(entity);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws TravelAgencyServiceException {
        try {
            vaucherDao.delete(id);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public Entity findById(int id) throws TravelAgencyServiceException {
        try {
            Entity vauher = vaucherDao.findById(id);
            return vauher;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public List<Entity> findAll() throws TravelAgencyServiceException {
        try {
            List<Entity> vauchers = vaucherDao.findAll();
            return vauchers;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }
}
