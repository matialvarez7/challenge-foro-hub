package com.github.matialvarez7.foro_hub.domain.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findByAutorId(Long id);
}
