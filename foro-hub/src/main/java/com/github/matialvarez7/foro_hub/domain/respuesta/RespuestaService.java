package com.github.matialvarez7.foro_hub.domain.respuesta;

import com.github.matialvarez7.foro_hub.domain.ValidacionException;
import com.github.matialvarez7.foro_hub.domain.topico.Estado;
import com.github.matialvarez7.foro_hub.domain.topico.Topico;
import com.github.matialvarez7.foro_hub.domain.topico.TopicoRepository;
import com.github.matialvarez7.foro_hub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class RespuestaService {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    RespuestaRepository respuestaRepository;

    public RegistroRespuestaResponse crearRespuesta(Long topicoId, @Valid RegistroRespuestaRequest respuesta, Usuario usuarioAutenticado) {

        Optional<Topico> topicoBuscado = topicoRepository.findById(topicoId);
        if (topicoBuscado.isEmpty()) {
            throw new ValidacionException("No existe el tópico al que hace referencia");
        }

        if (topicoBuscado.get().getEstado().equals(Estado.CERRADO)) {
            throw new ValidacionException("No es posible generar una respuesta para este topico");
        }

        Respuesta nuevaRespuesta = new Respuesta(respuesta, topicoBuscado.get(), usuarioAutenticado);
        respuestaRepository.save(nuevaRespuesta);
        return new RegistroRespuestaResponse(nuevaRespuesta);
    }
}
