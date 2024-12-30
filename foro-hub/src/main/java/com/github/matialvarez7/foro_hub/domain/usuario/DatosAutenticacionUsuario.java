package com.github.matialvarez7.foro_hub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosAutenticacionUsuario(
        @NotBlank
        @Email
        String correoElectronico,
        @NotBlank
        String clave
) {
}
