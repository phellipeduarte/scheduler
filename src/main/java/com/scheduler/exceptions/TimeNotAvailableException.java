package com.scheduler.exceptions;

public class TimeNotAvailableException extends RuntimeException {
    public TimeNotAvailableException() {
        super("O horário não está disponível.");
    }
}
