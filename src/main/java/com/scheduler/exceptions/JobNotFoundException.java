package com.scheduler.exceptions;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException() {
        super("Serviço não encontrado.");
    }
}
