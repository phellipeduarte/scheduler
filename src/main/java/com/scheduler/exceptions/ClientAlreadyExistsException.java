package com.scheduler.exceptions;

public class ClientAlreadyExistsException extends RuntimeException{

    public ClientAlreadyExistsException() {
        super("O cliente já existe.");
    }
}
