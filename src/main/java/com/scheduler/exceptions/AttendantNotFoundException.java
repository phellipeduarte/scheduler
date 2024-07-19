package com.scheduler.exceptions;

public class AttendantNotFoundException extends RuntimeException {

    public AttendantNotFoundException() {
        super("O atendente não existe.");
    }
}
