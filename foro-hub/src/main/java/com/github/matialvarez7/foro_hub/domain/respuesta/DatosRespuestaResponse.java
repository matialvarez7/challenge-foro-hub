package com.github.matialvarez7.foro_hub.domain.respuesta;

public record DatosRespuestaResponse(
        String tituloTopico,
        String autor,
        String mensajeRespuesta
) {
    public DatosRespuestaResponse(Respuesta respuesta) {
        this(respuesta.getTopico().getTitulo(), respuesta.getAutor().getNombre(), respuesta.getMensaje());
    }
}
