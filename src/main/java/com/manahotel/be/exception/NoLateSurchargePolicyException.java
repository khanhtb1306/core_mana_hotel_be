package com.manahotel.be.exception;

public class NoLateSurchargePolicyException extends RuntimeException{
    public NoLateSurchargePolicyException(String message) {
        super(message);
    }
}
