package by.epam.javatr.minchuk.project.service.impl;

import by.epam.javatr.minchuk.project.dao.DAOFactory;
import by.epam.javatr.minchuk.project.dao.TourDao;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.TourService;

import java.util.List;

/**
 * Class {@code TourServiceImpl}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class TourServiceImpl implements TourService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private TourDao tourDao = daoFactory.getTourDao();

    public TourServiceImpl() {
    }

    @Override
    public void setHotTour(int id, boolean isHot) throws TravelAgencyServiceException {
        if (id > 0) {
            try {
                tourDao.setHotTour(id, isHot);
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
                tourDao.create(entity);
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
                tourDao.update(entity);
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
                tourDao.delete(id);
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
                Entity tour = tourDao.findById(id);
                return tour;
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
            List<Entity> tours = tourDao.findAll();
            return tours;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }
}
