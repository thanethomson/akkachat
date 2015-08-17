package models.json;

/**
 * A generic class for handling JSON errors.
 */
public class JsonError extends JsonBase {

    /** The error message. */
    public String error = null;

    public JsonError() {}

    public JsonError(String error) {
        this.error = error;
    }

}
