package by.epam.javatr.minchuk.project.model.entity;

import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;

import java.util.Objects;

/**
 * Class {@code Room}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public class Room extends Entity {

    private String type;
    private double pricePerDay;

    public Room() {
    }

    public Room(int id, String type, double pricePerDay) {
        super(id);
        this.type = type;
        this.pricePerDay = pricePerDay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws TravelAgencyDataWrongException {
        if (type != null && type != "") {
            this.type = type;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect room type value.");
        }
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) throws TravelAgencyDataWrongException {
        if (pricePerDay >= 0) {
            this.pricePerDay = pricePerDay;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect pricePerDay value.");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Room room = (Room) o;
        return Double.compare(room.pricePerDay, pricePerDay) == 0 &&
                type.equals(room.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, pricePerDay);
    }

    @Override
    public String toString() {
        return "Room{" + super.toString() +
                "type='" + type + '\'' +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
