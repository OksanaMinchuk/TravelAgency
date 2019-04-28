package by.epam.javatr.minchuk.project.model.entity;

import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;
import by.epam.javatr.minchuk.project.model.exception.technicalexeption.TravelAgencyNullPointerException;

import java.util.Date;
import java.util.Objects;

/**
 * Class {@code Vaucher}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public class Vaucher {

    public enum CountryType {
        ALBANIA, AUSTRIA, BELARUS, BELGIUM, CYPRUS, CZECH_REPUBLIC, EGYPT, GERMANY, INDIA, MALTA, QATAR
    }

    private Date dateFrom;
    private Date dateTo;
    private boolean isHotTour;
    private double totalPrice;
    private Tour tour;
    private Transport transport;
    private CountryType country;
    private Hotel hotel;

    public Vaucher() {
    }

    public Vaucher(Date dateFrom, Date dateTo, boolean isHotTour, double totalPrice, Tour tour,
                   Transport transport, CountryType country, Hotel hotel) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.isHotTour = isHotTour;
        this.totalPrice = totalPrice;
        this.tour = tour;
        this.transport = transport;
        this.country = country;
        this.hotel = hotel;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public boolean isHotTour() {
        return isHotTour;
    }

    public void setHotTour(boolean hotTour) {
        isHotTour = hotTour;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) throws TravelAgencyDataWrongException {
        if (totalPrice >= 0) {
            this.totalPrice = totalPrice;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect totalPrice value.");
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

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) throws TravelAgencyDataWrongException {
        if (transport != null) {
            this.transport = transport;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect transport value.");
        }
    }

    public CountryType getCountry() {
        return country;
    }

    public void setCountry(CountryType country) throws TravelAgencyDataWrongException {
        if (country != null) {
            this.country = country;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect country value.");
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

    public double calculateTotalPrice (Tour tour, Transport transport) throws TravelAgencyNullPointerException {
        if (tour != null && transport != null) {
            return tour.getPrice() + transport.getTicketPrice();
        } else {
            throw new TravelAgencyNullPointerException("Invoking a method for a null object.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaucher vaucher = (Vaucher) o;
        return isHotTour == vaucher.isHotTour &&
                Double.compare(vaucher.totalPrice, totalPrice) == 0 &&
                dateFrom.equals(vaucher.dateFrom) &&
                dateTo.equals(vaucher.dateTo) &&
                tour.equals(vaucher.tour) &&
                transport.equals(vaucher.transport) &&
                country == vaucher.country &&
                hotel.equals(vaucher.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, dateTo, isHotTour, totalPrice, tour, transport, country, hotel);
    }

    @Override
    public String toString() {
        return "Vaucher{" + super.toString() +
                "dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", isHotTour=" + isHotTour +
                ", totalPrice=" + totalPrice +
                ", tour=" + tour +
                ", transport=" + transport +
                ", country=" + country +
                ", hotel=" + hotel +
                '}';
    }
}
