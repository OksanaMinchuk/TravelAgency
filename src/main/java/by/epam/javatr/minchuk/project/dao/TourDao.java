package by.epam.javatr.minchuk.project.dao;

/**
 * Interface {@code TourDao}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public interface TourDao extends EntityDao {

    void setHotTour(int id, boolean isHot);

}
