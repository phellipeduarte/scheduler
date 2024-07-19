package com.scheduler.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AttendantRequestDTO(
        @NotBlank(message = "É preciso informar o nome do atendente.") String name,
        String title,
        String description,
        String imageUrl,
        @NotNull(message = "É preciso informar o id do estabelecimento.") Integer establishmentId) {
}
