package models.actormessages;

/**
 * An error message returned by the manager actor if a user with the required username already exists.
 */
public class UserAlreadyExistsError extends ErrorMessage {

    public String username = null;

    public UserAlreadyExistsError() {}

    public UserAlreadyExistsError(String username) {
        super(String.format("The user with username %s already exists", username));
        this.username = username;
    }

}
