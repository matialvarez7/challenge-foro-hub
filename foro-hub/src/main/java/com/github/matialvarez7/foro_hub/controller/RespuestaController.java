package com.github.matialvarez7.foro_hub.controller;

import com.github.matialvarez7.foro_hub.domain.respuesta.DatosRespuestaResponse;
import com.github.matialvarez7.foro_hub.domain.respuesta.RegistroRespuestaRequest;
import com.github.matialvarez7.foro_hub.domain.respuesta.RegistroRespuestaResponse;
import com.github.matialvarez7.foro_hub.domain.respuesta.RespuestaService;
import com.github.matialvarez7.foro_hub.domain.usuario.AutenticacionUsuarioService;
import com.github.matialvarez7.foro_hub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    RespuestaService respuestaService;
    @Autowired
    AutenticacionUsuarioService autenticacionUsuarioService;

    @PostMapping("/topicos/{id}")
    public ResponseEntity<RegistroRespuestaResponse> crearRespuesta(@PathVariable(name = "id") Long topicoId, @RequestBody @Valid RegistroRespuestaRequest respuesta) {
        System.out.println(respuesta);
        Usuario usuarioAutenticado = autenticacionUsuarioService.obtenerUsuarioAutenticado();
        RegistroRespuestaResponse response = respuestaService.crearRespuesta(topicoId, respuesta, usuarioAutenticado);
        return ResponseEntity.ok(response);
    }

    // Trae todas las respuestas generadas por el usuario autenticado sin importar el tópico.
    @GetMapping
    public ResponseEntity<List<DatosRespuestaResponse>> obtenerTodasLasRespuestas() {
        Usuario usuarioAutenticado = autenticacionUsuarioService.obtenerUsuarioAutenticado();
        return ResponseEntity.ok(respuestaService.obtenerRespuestas(usuarioAutenticado.getId()));
    }
}
