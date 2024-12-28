package com.github.matialvarez7.foro_hub.domain.curso;

import com.github.matialvarez7.foro_hub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Curso")
@Table(name = "cursos")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicos;

    public Curso() {}

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        topicos.forEach(t -> t.setCurso(this));
        this.topicos = topicos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", topicos=" + topicos +
                '}';
    }
}