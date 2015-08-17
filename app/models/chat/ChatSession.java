package models.chat;

import akka.actor.ActorRef;

/**
 * Represents a chat session with a single user.
 */
public class ChatSession {

    /** The actor reference for the chat (in this case, the WebSockets session). */
    public ActorRef actorRef = null;
    /** The user's username. */
    public String username = null;

    public ChatSession() {}

    public ChatSession(ActorRef actorRef, String username) {
        this.actorRef = actorRef;
        this.username = username;
    }

}
