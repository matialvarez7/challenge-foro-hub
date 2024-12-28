package com.github.matialvarez7.foro_hub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNombreEquals(@NotBlank String nombre);
}
