package actors.chat;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.ChatMessageType;
import models.actormessages.*;
import models.chat.ChatRoom;
import models.chat.ChatSession;
import models.json.JsonChatMessage;
import models.json.JsonError;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the (WebSocket-based) interaction with a client.
 */
public class ChatSessionActor extends UntypedActor {

    public static Props props(String username, ActorRef chatManager, ActorRef out) {
        return Props.create(ChatSessionActor.class, username, chatManager, out);
    }

    /** The user's username for this session. */
    private final String username;
    /** The chat manager actor with which this chat session actor must interact. */
    private final ActorRef chatManager;
    /** The output actor for the WebSockets session. */
    private final ActorRef out;
    /** Maintaining a local cache of info about the various chat rooms available. */
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();

    public ChatSessionActor(String username, ActorRef chatManager, ActorRef out) {
        this.username = username;
        this.chatManager = chatManager;
        this.out = out;
    }

    @Override
    public void preStart() {
        // notify the manager that we're here!
        chatManager.tell(new StartSessionMessage(username), self());
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof UserAlreadyExistsError) {
            UserAlreadyExistsError err = (UserAlreadyExistsError)message;

            // send the error message
            out.tell(new JsonError(err.message).toString(), self());

        } else if (message instanceof String) {
            JsonNode json = new ObjectMapper().readTree((String)message);

            if (!json.hasNonNull("type")) {
                out.tell(new JsonError("Missing \"type\" identifier in incoming message").toString(), self());
            } else {
                ChatMessageType msgType = null;

                // parse the message according to what type it is
                try {
                    msgType = ChatMessageType.valueOf(json.get("type").asText());
                } catch (Exception e) {
                    e.printStackTrace();
                    out.tell(
                        new JsonError(String.format("Invalid message type: %s",
                                json.get("type").asText())).toString(),
                        self()
                    );
                    msgType = null;
                }

                switch (msgType) {
                    case SEND_MESSAGE:
                        sendChatMessage((String)message);
                        break;
                    case CREATE_ROOM:
                        break;
                    case DELETE_ROOM:
                        break;
                }
            }
        } else if (message instanceof GetChatRoomResponse) {
            GetChatRoomResponse response = (GetChatRoomResponse)message;

            // keep track of the chat room
            chatRooms.put(response.room.name, response.room);

            // now send the message, if any
            if (response.futureMsg != null) {
                sendChatMessage(response.room, response.futureMsg);
            }
        }
    }


    public void sendChatMessage(String message) {
        JsonChatMessage msg = null;

        try {
            msg = new ObjectMapper().readValue(message, JsonChatMessage.class);
        } catch (IOException e) {
            out.tell(new JsonError(e.getMessage()), self());
            msg = null;
        }

        if (msg != null) {
            ChatMessage chatMessage = new ChatMessage(
                    msg.uid,
                    msg.room,
                    username,
                    msg.content,
                    new Date()
            );

            // if we've got a cached version of the chat room's info
            if (chatRooms.containsKey(msg.room)) {
                // tell the room to broadcast the message to everyone else
                sendChatMessage(chatRooms.get(msg.room), chatMessage);
            } else {
                // ask the manager to tell us about this chat room - we'll send the message later
                chatManager.tell(new GetChatRoomMessage(msg.room, chatMessage), self());
            }
        }
    }

    public void sendChatMessage(ChatRoom room, ChatMessage msg) {
        room.actorRef.tell(msg, self());
    }

}
