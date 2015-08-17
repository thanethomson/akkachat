package models.actormessages;

/**
 * Allows a chat session actor to query the chat room manager about a particular chat room.
 */
public class GetChatRoomMessage {

    /** The name of the chat room for which we must fetch the info. */
    public String name = null;
    /** The message to send in future, if any. */
    public ChatMessage futureMsg = null;

    public GetChatRoomMessage() {}

    public GetChatRoomMessage(String name, ChatMessage futureMsg) {
        this.name = name;
        this.futureMsg = futureMsg;
    }

}
