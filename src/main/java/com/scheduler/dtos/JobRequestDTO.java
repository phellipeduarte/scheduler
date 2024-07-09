package com.scheduler.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record JobRequestDTO(@NotBlank(message = "É preciso informar o nome do serviço.") String name,
                            @NotNull @Positive Double price,
                            @NotNull @Positive Integer durationMinutes,
                            @NotNull(message = "É preciso informar o id do estabelecimento.") Integer establishmentId) {
}
