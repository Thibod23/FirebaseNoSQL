package com.example.a533.cours5nosql.Notification.model;

public class ImportantMessageModel {

    private String messageImportant;
    private String sender;

    public ImportantMessageModel() {}

    public ImportantMessageModel(String message, String sender) {
        this.messageImportant = message;
        this.sender = sender;
    }

    public String getMessage() {
        return messageImportant;
    }

    public void setMessage(String message) {
        this.messageImportant = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
