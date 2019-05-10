package by.epam.javatr.minchuk.project.service.impl;

import by.epam.javatr.minchuk.project.dao.DAOFactory;
import by.epam.javatr.minchuk.project.dao.HotelDao;
import by.epam.javatr.minchuk.project.model.entity.Entity;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyDAOException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyServiceException;
import by.epam.javatr.minchuk.project.service.HotelService;

import java.util.List;

/**
 * Class {@code HotelServiceImpl}
 *
 * @author Oksana Minchuk
 * @version 1.0 10/05/2019
 */

public class HotelServiceImpl implements HotelService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private HotelDao hotelDao = daoFactory.getHotelDao();

    public HotelServiceImpl() {
    }

    @Override
    public void create(Entity entity) throws TravelAgencyServiceException {
        try {
            hotelDao.create(entity);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void update(Entity entity) throws TravelAgencyServiceException {
        try {
            hotelDao.update(entity);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public void delete(int id) throws TravelAgencyServiceException {
        try {
            hotelDao.delete(id);
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public Entity findById(int id) throws TravelAgencyServiceException {
        try {
            Entity hotel = hotelDao.findById(id);
            return hotel;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }

    @Override
    public List<Entity> findAll() throws TravelAgencyServiceException {
        try {
            List<Entity> hotels = hotelDao.findAll();
            return  hotels;
        } catch (TravelAgencyDAOException e) {
            throw new TravelAgencyServiceException(e);
        }
    }
}
