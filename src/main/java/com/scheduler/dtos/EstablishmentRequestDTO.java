package com.scheduler.dtos;

import jakarta.validation.constraints.NotBlank;

public record EstablishmentRequestDTO(@NotBlank(message = "O estabelecimento precisa ter nome.") String name) {
}
