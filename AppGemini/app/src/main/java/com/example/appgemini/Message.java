package com.example.appgemini;

public class Message {
    private String role; // user ou assistant
    private String content;
    private long timestamp;

    public Message(String role, String content, long timestamp) {
        this.role = role;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
