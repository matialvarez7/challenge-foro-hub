package com.github.matialvarez7.foro_hub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

public record RegistroRespuestaRequest(
        @NotBlank
        String mensaje
) {
}
