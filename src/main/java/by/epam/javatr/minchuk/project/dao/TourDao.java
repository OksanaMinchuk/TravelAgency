package by.epam.javatr.minchuk.project.dao;

public interface TourDao extends EntityDao {

    void setHotTour(int id, boolean isHot);

}
