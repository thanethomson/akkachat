package models.chat;

import akka.actor.ActorRef;

import java.util.Set;

/**
 * Model of a chat room.
 */
public class ChatRoom {

    /** A reference to the actor facilitating this room. */
    public ActorRef actorRef = null;
    /** A short, descriptive name for the chat room. */
    public String name = null;
    /** A more detailed description for this room. */
    public String description = null;
    /** A set of usernames of admins for the room. */
    public Set<String> admins = null;

    public ChatRoom() {}

    public ChatRoom(
            ActorRef actorRef,
            String name,
            String description,
            Set<String> admins) {
        this.actorRef = actorRef;
        this.name = name;
        this.description = description;
        this.admins = admins;
    }

}
