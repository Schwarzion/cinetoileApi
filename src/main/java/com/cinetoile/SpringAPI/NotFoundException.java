package com.cinetoile.SpringAPI;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String type, String id) {
        super("Could not find " + type + id);
    }
}
