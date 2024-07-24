package com.scheduler.exceptions;

public class OccupationNotFoundException extends RuntimeException {

    public OccupationNotFoundException() {
        super("A carga horária não encontrada.");
    }
}
