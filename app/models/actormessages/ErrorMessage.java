package models.actormessages;

/**
 * A generic base class for error messages.
 */
public abstract class ErrorMessage {

    public String message = null;

    public ErrorMessage() {}

    public ErrorMessage(String message) {
        this.message = message;
    }

}
