package by.epam.javatr.minchuk.project.model.entity;

import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;

import java.util.Objects;

/**
 * Class {@code Transport}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public class Transport extends Entity {

    public enum TransportType {
        PLAIN, TRAIN, BUS, AUTO
    }

    private TransportType type;
    private double ticketPrice;

    public Transport() {
    }

    public Transport(int id, TransportType type, double ticketPrice) {
        super(id);
        this.type = type;
        this.ticketPrice = ticketPrice;
    }

    public TransportType getType() {
        return type;
    }

    public void setType(TransportType type) throws TravelAgencyDataWrongException {
        if (type != null) {
            this.type = type;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect Transport Type value.");
        }
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) throws TravelAgencyDataWrongException {
        if (ticketPrice >= 0) {
            this.ticketPrice = ticketPrice;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect ticketPrice value.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Transport transport = (Transport) o;
        return Double.compare(transport.ticketPrice, ticketPrice) == 0 &&
                type == transport.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, ticketPrice);
    }

    @Override
    public String toString() {
        return "Transport{" + super.toString() +
                "type=" + type +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
