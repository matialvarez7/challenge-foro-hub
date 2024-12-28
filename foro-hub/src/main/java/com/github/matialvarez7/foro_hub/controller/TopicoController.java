package com.github.matialvarez7.foro_hub.controller;

import com.github.matialvarez7.foro_hub.domain.topico.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<RegistroTopicoResponse> registrarTopico(@RequestBody @Valid RegistroTopicoRequest datos) {
        System.out.println(datos);
        RegistroTopicoResponse response = topicoService.registrarTopico(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DetalleTopicoResponse>> listarTopicos() {
        List<DetalleTopicoResponse> response = topicoService.listarTopicos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleTopicoResponse> obtenerTopico(@PathVariable @NotNull Long id) {
        DetalleTopicoResponse response = topicoService.obtenerTopico(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ModificacionTopicoResponse> modificarTopico(@PathVariable Long id, @RequestBody ModificacionTopicoRequest datosModificacion) {
        ModificacionTopicoResponse response = topicoService.actualizarTopico(id, datosModificacion);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.ok().build();
    }
}
