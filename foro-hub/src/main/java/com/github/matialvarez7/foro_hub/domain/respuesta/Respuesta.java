package com.github.matialvarez7.foro_hub.domain.respuesta;


import com.github.matialvarez7.foro_hub.domain.topico.Topico;
import com.github.matialvarez7.foro_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    private Boolean solucion;

    public Respuesta(){};

    public Respuesta (RegistroRespuestaRequest registro, Topico topico, Usuario usuario) {
        this.mensaje = registro.mensaje();
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.autor = usuario;
        this.solucion = false;
    }

    public Long getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Topico getTopico() {
        return topico;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Boolean getSolucion() {
        return solucion;
    }

//    @Override
//    public String toString() {
//        return "Respuesta{" +
//                "id=" + id +
//                ", mensaje='" + mensaje + '\'' +
//                ", topico=" + topico +
//                ", fechaCreacion=" + fechaCreacion +
//                ", autor=" + autor +
//                ", solucion=" + solucion +
//                '}';
//    }
}
