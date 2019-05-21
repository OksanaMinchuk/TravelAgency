package by.epam.javatr.minchuk.project.service.impl;

import by.epam.javatr.minchuk.project.dao.DAOFactory;
import by.epam.javatr.minchuk.project.dao.VaucherDao;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.entity.Vaucher;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.ServiceFactory;
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
        if (country != null) {
            try {
                List<Vaucher> vauchers = vaucherDao.getVauchersByCountry(country);
                return vauchers;
            } catch (TravelAgencyDAOException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public List<Vaucher> getVauchersByTourType(String type) throws TravelAgencyServiceException {
        if (type != null) {
            try {
                List<Vaucher> vauchers = vaucherDao.getVauchersByTourType(type);
                return vauchers;
            } catch (TravelAgencyDAOException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void create(Entity entity) throws TravelAgencyServiceException {
        if (entity != null) {
            try {
                vaucherDao.create(entity);
            } catch (TravelAgencyDAOException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void update(Entity entity) throws TravelAgencyServiceException {
        if (entity != null) {
            try {
                vaucherDao.update(entity);
            } catch (TravelAgencyDAOException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public void delete(int id) throws TravelAgencyServiceException {
        if (id > 0) {
            try {
                vaucherDao.delete(id);
            } catch (TravelAgencyDAOException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
        }
    }

    @Override
    public Entity findById(int id) throws TravelAgencyServiceException {
        if (id > 0) {
            try {
                Entity vauher = vaucherDao.findById(id);
                return vauher;
            } catch (TravelAgencyDAOException e) {
                throw new TravelAgencyServiceException(e);
            }
        } else {
            throw new TravelAgencyServiceException("Incorrect parameters.");
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
