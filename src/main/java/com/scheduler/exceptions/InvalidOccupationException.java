package com.scheduler.exceptions;

public class InvalidOccupationException extends RuntimeException {
    public InvalidOccupationException() {
        super("A carga horária é inválida.");
    }
}
