package com.github.matialvarez7.foro_hub.domain.usuario;

import com.github.matialvarez7.foro_hub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String clave;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicosCreados;

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getClave() {
        return clave;
    }

    public List<Topico> getTopicosCreados() {
        return topicosCreados;
    }

    public void setTopicosCreados(List<Topico> topicosCreados) {
        topicosCreados.forEach(t -> t.setAutor(this));
        this.topicosCreados = topicosCreados;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", clave='" + clave + '\'' +
                ", topicosCreados=" + topicosCreados +
                '}';
    }
}
