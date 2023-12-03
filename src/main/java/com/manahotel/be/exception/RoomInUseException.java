package com.manahotel.be.exception;

public class RoomInUseException extends RuntimeException {
    public RoomInUseException(String message) {
        super(message);
    }
}
