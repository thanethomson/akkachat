package models.actormessages;

/**
 * Sent by a ChatRoomActor when starting a session.
 */
public class StartSessionMessage {

    /** The username of the user starting the session. */
    public String username = null;

    public StartSessionMessage() {}

    public StartSessionMessage(String username) {
        this.username = username;
    }

}
