package com.example.hutchhub.Models;

public class Message {

    String message, name, key;

    public Message() {
    }

    public Message(String message, String name, String key) {
        this.message = message;
        this.name = name;
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
