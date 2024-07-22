package com.scheduler.exceptions;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException() {
        super("O horário não existe.");
    }
}
