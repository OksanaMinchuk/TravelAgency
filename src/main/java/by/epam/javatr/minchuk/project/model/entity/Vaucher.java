package by.epam.javatr.minchuk.project.model.entity;

import by.epam.javatr.minchuk.project.model.entity.type.TransportType;
import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyNullPointerException;

import java.util.Objects;

/**
 * Class {@code Vaucher}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public class Vaucher {

    public enum CountryType {
        AUSTRIA, BELARUS, BELGIUM, CYPRUS, CZECH_REPUBLIC, EGYPT, GERMANY, INDIA, MALTA
    }

    private static final double HOT_TOUR_CONSTANT = 0.8;

    private String country;
    private int nights;
    private double totalPrice;
    private Tour tour;
    private TransportType transport;
    private Hotel hotel;

    public Vaucher() {
    }

    public Vaucher(String country, int nights, double totalPrice, Tour tour, TransportType transport, Hotel hotel) {
        this.country = country;
        this.nights = nights;
        this.totalPrice = totalPrice;
        this.tour = tour;
        this.transport = transport;
        this.hotel = hotel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws TravelAgencyDataWrongException {
        if (country != null) {
            this.country = country;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect country value.");
        }
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) throws TravelAgencyDataWrongException {
        if (nights > 0) {
            this.nights = nights;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect country nights.");
        }
    }

    /**
     * Method calculate total price depends on tour and hotel price and user's discount.
     * @param user
     * @return total price
     * @throws TravelAgencyNullPointerException
     */
    public double getPrice (User user) throws TravelAgencyNullPointerException {
        if (user != null) {
            double totalPrice = (tour.getPrice() + hotel.getPricePerDay() * nights) * (1 - user.getDiscount()/100);
            if (tour.isHot()) {
                totalPrice *= HOT_TOUR_CONSTANT;
            }
            return  totalPrice;
        } else {
            throw new TravelAgencyNullPointerException("Invoking a method for a null object.");
        }
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) throws TravelAgencyDataWrongException {
        if (tour != null) {
            this.tour = tour;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect tour value.");
        }
    }

    public TransportType getTransport() {
        return transport;
    }

    public void setTransport(TransportType transport) throws TravelAgencyDataWrongException {
        if (transport != null) {
            this.transport = transport;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect transport value.");
        }
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) throws TravelAgencyDataWrongException {
        if (hotel != null) {
            this.hotel = hotel;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect hotel value.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaucher vaucher = (Vaucher) o;
        return nights == vaucher.nights &&
                Double.compare(vaucher.totalPrice, totalPrice) == 0 &&
                country == vaucher.country &&
                tour.equals(vaucher.tour) &&
                transport.equals(vaucher.transport) &&
                hotel.equals(vaucher.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, nights, totalPrice, tour, transport, hotel);
    }

    @Override
    public String toString() {
        return "Vaucher{" + super.toString() +
                "country=" + country +
                ", nights=" + nights +
                ", totalPrice=" + totalPrice +
                ", tour=" + tour +
                ", transport=" + transport +
                ", hotel=" + hotel +
                '}';
    }
}
