package models.json;

/**
 * Base model for an incoming message, with message type for quicker identification.
 */
public class JsonIncomingMessageBase extends JsonBase {

    /** What type of message is this? */
    public String type = null;
    /** Some kind of unique identifier with which outgoing messages should be associated for recombination at the client. */
    public String uid = null;

    public JsonIncomingMessageBase() {}

    public JsonIncomingMessageBase(String type, String uid) {
        this.type = type;
        this.uid = uid;
    }

}
