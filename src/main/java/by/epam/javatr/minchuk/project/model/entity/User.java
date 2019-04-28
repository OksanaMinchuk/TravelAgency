package by.epam.javatr.minchuk.project.model.entity;

import by.epam.javatr.minchuk.project.model.exception.logicexeption.TravelAgencyDataWrongException;

import java.util.Objects;

/**
 * Class {@code User}
 *
 * @author Oksana Minchuk
 * @version 1.0 28/04/2019
 */

public class User extends Entity {

    public enum RoleType {
        ADMIN, VISITOR, CLIENT, MANAGER
    }

    private String name;
    private String surname;
    private String email;
    private double discount;
    private String login;
    private String password;
    private RoleType role;

    public User() {
    }

    public User(int id, String name, String surname, String email,
                double discount, String login, String password, RoleType role) {
        super(id);
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.discount = discount;
            this.login = login;
            this.password = password;
            this.role = role;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws TravelAgencyDataWrongException {
        if (surname != null && surname != "") {
            this.surname = surname;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect surname value.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws TravelAgencyDataWrongException {
        if (email != null && email != "") {
            this.email = email;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect email value.");
        }
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) throws TravelAgencyDataWrongException {
        if (discount >= 0) {
            this.discount = discount;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect discount value.");
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws TravelAgencyDataWrongException {
        if (login != null && login != "") {
            this.login = login;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect login value.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws TravelAgencyDataWrongException {
        if (password != null && password != "") {
            this.password = password;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect password value.");
        }
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) throws TravelAgencyDataWrongException {
        if (role != null) {
            this.role = role;
        } else {
            throw new TravelAgencyDataWrongException("Incorrect role value.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Double.compare(user.discount, discount) == 0 &&
                name.equals(user.name) &&
                surname.equals(user.surname) &&
                email.equals(user.email) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, email, discount, login, password, role);
    }

    @Override
    public String toString() {
        return "User{" + super.toString() +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", discount=" + discount +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
