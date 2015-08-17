package models.actormessages;

/**
 * Message to instantiate a new chat room. To be sent to the ChatManagerActor instance when a ChatSessionActor wants
 * to create a new chat room.
 */
public class NewRoomMessage {
    
    /** The name for the new chat room. */
    public String name = null;
    /** A description for the chat room. */
    public String description = null;
    /** The username of the user creating the new room (to be one of the new admins). */
    public String username = null;

    public NewRoomMessage() {}

    public NewRoomMessage(String name, String description, String username) {
        this.name = name;
        this.description = description;
        this.username = username;
    }

}
