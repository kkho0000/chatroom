package space.kkho.chatroom.domain;

import java.time.LocalDateTime;

public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;
    private String timestamp;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE,
        CONFIRM
    }

    // 默认构造函数
    public ChatMessage() {}

    public ChatMessage(String content, String sender, MessageType type) {
        this.content = content;
        this.sender = sender;
        this.type = type;
        this.timestamp = LocalDateTime.now().toString();
    }

    // 带参数的构造函数
    public ChatMessage(String content, String sender, MessageType type, String timestamp) {
        this.content = content;
        this.sender = sender;
        this.type = type;
        this.timestamp = timestamp;
    }

    // Getter and Setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getter and Setter for sender
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    // Getter and Setter for type
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
