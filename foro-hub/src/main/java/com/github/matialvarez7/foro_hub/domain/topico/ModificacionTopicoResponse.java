package com.github.matialvarez7.foro_hub.domain.topico;

import java.time.LocalDateTime;

public record ModificacionTopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaModificacion
) {
    public ModificacionTopicoResponse(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaModificacion());
    }
}
