package by.epam.javatr.minchuk.project.model.exception.technicalexeption;

public class TravelAgencyNullPointerException extends TravelAgencyTechnicException {

    public TravelAgencyNullPointerException() {
    }

    public TravelAgencyNullPointerException(String message) {
        super(message);
    }

    public TravelAgencyNullPointerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyNullPointerException(Throwable cause) {
        super(cause);
    }
}
