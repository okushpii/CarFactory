package com.training.carfactory.model.exception;

public class PartIsMissingException extends RuntimeException {

    public PartIsMissingException(String message) {
        super(message);
    }
}
