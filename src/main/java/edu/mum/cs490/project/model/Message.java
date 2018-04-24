package edu.mum.cs490.project.model;

/**
 * Created by Erdenebayar on 4/24/2018
 */
public class Message {

    public enum Type {
        SUCCESS, FAILED, INFO
    }

    public Message() {
    }

    public Message(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    private Type type;
    private String message;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
