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
        try {
            tourDao.setHotTour(id, isHot);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void create(Entity entity) throws TravelAgencyServiceException {
        try {
            tourDao.create(entity);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void update(Entity entity) throws TravelAgencyServiceException {
        try {
            tourDao.update(entity);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws TravelAgencyServiceException {
        try {
            tourDao.delete(id);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public Entity findById(int id) throws TravelAgencyServiceException {
        try {
            Entity tour = tourDao.findById(id);
            return tour;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
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
