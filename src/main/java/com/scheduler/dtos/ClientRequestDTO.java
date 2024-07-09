package com.scheduler.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClientRequestDTO(@NotBlank(message = "É preciso informar o nome do cliente.") String name,
                               @NotBlank(message = "É preciso informar o número do cliente.") String phone) {
}
