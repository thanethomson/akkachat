package models.actormessages;

import akka.actor.ActorRef;

import java.util.Date;

/**
 * A chat message as sent from a chat session actor to the chat room actor, and vice-versa for broadcasting of
 * chat messages.
 */
public class ChatMessage {

    /** The UID of the original message sent by the client. */
    public String uid = null;
    /** The name of the chat room for which this message was destined. */
    public String room = null;
    /** The content of this message. */
    public String content = null;
    /** The username of the sender of this message. */
    public String senderUsername = null;
    /** When was this message sent? */
    public Date sent = null;

    public ChatMessage() {}

    public ChatMessage(String uid, String room, String senderUsername, String content, Date sent) {
        this.uid = uid;
        this.room = room;
        this.senderUsername = senderUsername;
        this.content = content;
        this.sent = sent;
    }

}
