package com.github.matialvarez7.foro_hub.controller;

import com.github.matialvarez7.foro_hub.domain.respuesta.RegistroRespuestaRequest;
import com.github.matialvarez7.foro_hub.domain.respuesta.RegistroRespuestaResponse;
import com.github.matialvarez7.foro_hub.domain.respuesta.RespuestaService;
import com.github.matialvarez7.foro_hub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    RespuestaService respuestaService;

    @PostMapping("/topicos/{id}")
    public ResponseEntity<RegistroRespuestaResponse> crearRespuesta(@PathVariable(name = "id") Long topicoId, @RequestBody @Valid RegistroRespuestaRequest respuesta) {
        System.out.println(respuesta);
        Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RegistroRespuestaResponse response = respuestaService.crearRespuesta(topicoId, respuesta, usuarioAutenticado);
        return ResponseEntity.ok(response);
    }
}
