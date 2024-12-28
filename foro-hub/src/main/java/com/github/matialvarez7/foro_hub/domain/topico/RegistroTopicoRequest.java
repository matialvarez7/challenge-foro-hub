package com.github.matialvarez7.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroTopicoRequest(
        @NotNull
        Long idUsuario,
        @NotBlank
        String mensaje,
        @NotBlank
        String nombreCurso,
        @NotBlank
        String titulo
) {
}
