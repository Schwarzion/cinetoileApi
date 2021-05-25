package com.cinetoile.SpringAPI.dto.dtoOut;

public class MessageDTOOut {
    private String message;

    public MessageDTOOut(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}