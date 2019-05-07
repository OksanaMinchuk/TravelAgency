package by.epam.javatr.minchuk.project.model.entity;

import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;

public class Order extends Entity {

    private Vaucher vaucher;
    private User user;

    public Order() {
    }

    public Order(int id, Vaucher vaucher, User user) {
        super(id);
        this.vaucher = vaucher;
        this.user = user;
    }

    public Vaucher getVaucher() {
        return vaucher;
    }

    public void setVaucher(Vaucher vaucher) throws TravelAgencyDataWrongException {
        if (vaucher != null) {
            this.vaucher = vaucher;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect vaucher value.");
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) throws TravelAgencyDataWrongException {
        if (user != null) {
            this.user = user;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect user value.");
        }
    }
}
