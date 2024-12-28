package com.github.matialvarez7.foro_hub.domain.topico;

import java.time.LocalDateTime;

public record RegistroTopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion
) {
    public RegistroTopicoResponse(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion());
    }
}
