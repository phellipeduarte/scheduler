package com.scheduler.exceptions;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("O cliente não existe.");
    }
}
