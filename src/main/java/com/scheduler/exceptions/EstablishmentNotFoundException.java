package com.scheduler.exceptions;

public class EstablishmentNotFoundException extends RuntimeException {

    public EstablishmentNotFoundException() {
        super("Estabelecimento não encontrado.");
    }
}
