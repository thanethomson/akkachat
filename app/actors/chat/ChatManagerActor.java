package actors.chat;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import models.chat.ChatRoom;
import models.chat.ChatSession;

import java.util.HashMap;
import java.util.Map;

/**
 * The central management actor for handling meta-information regarding the global chat state.
 *
 * TODO: Implement functionality to interface with other hosts' chat manager actors.
 */
public class ChatManagerActor extends UntypedActor {

    public static Props props() {
        return Props.create(ChatManagerActor.class);
    }

    // a map for linking chat room names to actors and their details
    private final Map<String, ChatRoom> chatRooms = new HashMap<>();
    // a map for linking chat usernames to actors and their details
    private final Map<String, ChatSession> chatSessions = new HashMap<>();

    public ChatManagerActor() {}

    @Override
    public void onReceive(Object message) throws Exception {

    }

}
