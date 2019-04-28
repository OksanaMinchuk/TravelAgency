package by.epam.javatr.minchuk.project.model.entity;

import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;

import java.util.Objects;

/**
 * Class {@code Tour}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public class Tour extends Entity {

    public enum TourType {
        BEACH_TOUR, EXCURSION, FITNES_TOUR, FESTIVAL, WEEKEND_TOUR, SHOPPING,
    }

    private TourType type;
    private double price;

    public Tour() {
    }

    public Tour(int id, TourType type, double price) {
        super(id);
        this.type = type;
        this.price = price;
    }

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) throws TravelAgencyDataWrongException {
        if (type != null) {
            this.type = type;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect Tour Type value.");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws TravelAgencyDataWrongException {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect price value.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tour tour = (Tour) o;
        return Double.compare(tour.price, price) == 0 &&
                type == tour.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, price);
    }

    @Override
    public String toString() {
        return "Tour{" + super.toString() +
                "type=" + type +
                ", price=" + price +
                '}';
    }

}
