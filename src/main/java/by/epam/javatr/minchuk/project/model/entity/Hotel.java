package by.epam.javatr.minchuk.project.model.entity;

import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class {@code Hotel}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public class Hotel extends Entity {

    private String name;
    private List<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
    }

    public Hotel(int id, String name, List<Room> rooms) {
        super(id);
        this.name = name;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TravelAgencyDataWrongException {
        if (name != null && name != "") {
            this.name = name;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect name value.");
        }
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) throws TravelAgencyDataWrongException {
        if (rooms != null) {
            this.rooms = rooms;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect rooms value.");
        }
    }

    public Room getRoomByIndex(int index) throws TravelAgencyDataWrongException {
        if (index >= 0 && index < rooms.size()) {
            return rooms.get(index);
        } else {
            throw new TravelAgencyDataWrongException("Incorrect index room value.");
        }
    }

    public void addRoom(Room room) throws TravelAgencyDataWrongException {
        if (room != null) {
            rooms.add(room);
        } else {
            throw new TravelAgencyDataWrongException("Incorrect room value.");
        }
    }

    public void removeRoom(Room room) throws TravelAgencyDataWrongException {
        if (room != null) {
            rooms.remove(room);
        } else {
            throw new TravelAgencyDataWrongException("Incorrect room value.");
        }
    }

    public void removeRoomByIndex(int index) throws TravelAgencyDataWrongException {
        if (index >= 0 && index < rooms.size()) {
            rooms.remove(index);
        } else {
            throw new TravelAgencyDataWrongException("Incorrect index room value.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hotel hotel = (Hotel) o;
        return name.equals(hotel.name) &&
                rooms.equals(hotel.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, rooms);
    }

    @Override
    public String toString() {
        return "Hotel{" + super.toString() +
                "name='" + name + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
