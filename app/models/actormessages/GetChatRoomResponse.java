package models.actormessages;

import models.chat.ChatRoom;

/**
 * The response from the chat manager when asking for details regarding a particular chat room.
 */
public class GetChatRoomResponse {

    /** The chat room we requested. */
    public ChatRoom room = null;
    /** The future message we now have to convey to the room. */
    public ChatMessage futureMsg = null;

    public GetChatRoomResponse() {}

    public GetChatRoomResponse(ChatRoom room, ChatMessage futureMsg) {
        this.room = room;
        this.futureMsg = futureMsg;
    }

}
