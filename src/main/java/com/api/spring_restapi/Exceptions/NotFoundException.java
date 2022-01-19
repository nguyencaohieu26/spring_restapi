package com.api.spring_restapi.Exceptions;

//Custom Not Found Exception
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){super(message);}
}
