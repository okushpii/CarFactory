package com.training.carfactory.model.exception;

public class IncorrectPartException extends RuntimeException {

    public IncorrectPartException(String message){
       super(message);
    }
}
