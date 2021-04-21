package com.example.eurovision_cities.exceptions;

public class InternalException extends RuntimeException{
    public InternalException(Exception e){
        super(e);
    }

    public InternalException(String message){
        super(message);
    }
}
