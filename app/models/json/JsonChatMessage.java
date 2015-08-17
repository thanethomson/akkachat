package models.json;

import enums.ChatMessageType;

/**
 * A chat message from a single user for a specific chat room.
 */
public class JsonChatMessage extends JsonIncomingMessageBase {

    /** The name of the chat room in which to send this message. */
    public String room = null;
    /** The actual text content of the chat message. */
    public String content = null;

    public JsonChatMessage() {}

    public JsonChatMessage(String uid, String room, String content) {
        super(ChatMessageType.SEND_MESSAGE.toString(), uid);
        this.room = room;
        this.content = content;
    }

}
