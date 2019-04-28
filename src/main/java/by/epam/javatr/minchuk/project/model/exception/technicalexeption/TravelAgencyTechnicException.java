package by.epam.javatr.minchuk.project.model.exception.technicalexeption;

import by.epam.javatr.minchuk.project.model.exception.TravelAgencyException;

public class TravelAgencyTechnicException extends TravelAgencyException {

    public TravelAgencyTechnicException() {
    }

    public TravelAgencyTechnicException(String message) {
        super(message);
    }

    public TravelAgencyTechnicException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyTechnicException(Throwable cause) {
        super(cause);
    }
}
