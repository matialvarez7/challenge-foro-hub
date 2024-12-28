package com.github.matialvarez7.foro_hub.domain.topico;

import com.github.matialvarez7.foro_hub.domain.ValidacionException;
import com.github.matialvarez7.foro_hub.domain.curso.Curso;
import com.github.matialvarez7.foro_hub.domain.curso.CursoRepository;
import com.github.matialvarez7.foro_hub.domain.usuario.Usuario;
import com.github.matialvarez7.foro_hub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public RegistroTopicoResponse registrarTopico(@Valid RegistroTopicoRequest datos) {

        Usuario autor;
        Optional<Usuario> usuario = usuarioRepository.findById(datos.idUsuario());
        if (usuario.isPresent()){
            autor = usuario.get();
        } else {
            throw new ValidacionException("El id de usuario no existe");
        }
        Curso curso = cursoRepository.findByNombreEquals(datos.nombreCurso());
        if (curso == null) {
            throw new ValidacionException("El curso no existe");
        }
        Topico nuevoTopico = topicoRepository.existeTopicoEnElCurso(datos.titulo(), datos.mensaje(), datos.nombreCurso());
        if (nuevoTopico != null) {
            throw new ValidacionException("Ya existe un tópico similar en el foro");
        }

        nuevoTopico = new Topico(datos, autor, curso);
        topicoRepository.save(nuevoTopico);
        return new RegistroTopicoResponse(nuevoTopico);
    }

    public List<DetalleTopicoResponse> listarTopicos() {
        return topicoRepository.findAll().stream().map(DetalleTopicoResponse::new).collect(Collectors.toList());
    }

    public DetalleTopicoResponse obtenerTopico(Long id) {
        Optional<Topico> topicoBuscado = topicoRepository.findById(id);
        if (topicoBuscado.isPresent()) {
            return new DetalleTopicoResponse(topicoBuscado.get());
        } else {
            throw new ValidacionException("No existe el topico");
        }
    }

    public ModificacionTopicoResponse actualizarTopico(Long id, ModificacionTopicoRequest datosModificacion) {
        if (datosModificacion.titulo() == null && datosModificacion.mensaje() == null) {
            throw new ValidacionException("No se enviaron datos para realizar modificación");
        }
        Optional<Topico> topicoBuscado = topicoRepository.findById(id);
        if(topicoBuscado.isPresent()){
            Topico topico = topicoBuscado.get();
            topico.actualizarTopico(datosModificacion);
            return new ModificacionTopicoResponse(topico);
        } else {
            throw new ValidacionException("No existe el topico que desea modificar");
        }
    }

    public void eliminarTopico(Long id) {
        Optional<Topico> topicoBuscado = topicoRepository.findById(id);
        if (topicoBuscado.isPresent()) {
            topicoRepository.deleteById(id);
        } else {
            throw new ValidacionException("No existe el topico que desea eliminar");
        }
    }
}
