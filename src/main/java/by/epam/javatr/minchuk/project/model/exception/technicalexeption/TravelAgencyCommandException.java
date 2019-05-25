package by.epam.javatr.minchuk.project.model.exception.technicalexeption;

public class TravelAgencyCommandException extends TravelAgencyTechnicException {

    public TravelAgencyCommandException() {
    }

    public TravelAgencyCommandException(String message) {
        super(message);
    }

    public TravelAgencyCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelAgencyCommandException(Throwable cause) {
        super(cause);
    }
}
