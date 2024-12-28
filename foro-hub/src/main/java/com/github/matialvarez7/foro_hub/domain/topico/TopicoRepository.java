package com.github.matialvarez7.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("""
            select t from Topico t where t.titulo = :titulo and t.mensaje = :mensaje
            and t.curso.nombre = :nombreCurso
            """)
    Topico existeTopicoEnElCurso(@NotBlank String titulo, @NotBlank String mensaje, @NotBlank String nombreCurso);

}
