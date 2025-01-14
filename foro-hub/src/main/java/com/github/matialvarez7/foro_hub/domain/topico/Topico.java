package com.github.matialvarez7.foro_hub.domain.topico;

import com.github.matialvarez7.foro_hub.domain.curso.Curso;
import com.github.matialvarez7.foro_hub.domain.respuesta.Respuesta;
import com.github.matialvarez7.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;

    public Topico(){}

    public Topico(RegistroTopicoRequest datos, Usuario autor, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
        this.estado = Estado.ABIERTO;
        this.autor = autor;
        this.curso = curso;
        this.respuestas = new ArrayList<>();
    }

    public void actualizarTopico(ModificacionTopicoRequest datosModificacion) {
        if (datosModificacion.mensaje() != null) {
            this.mensaje = datosModificacion.mensaje();
            this.fechaModificacion = LocalDateTime.now();
        }
        if (datosModificacion.titulo() != null) {
            this.titulo = datosModificacion.mensaje();
            this.fechaModificacion = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
