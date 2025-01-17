package com.github.matialvarez7.foro_hub.domain.usuario;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionUsuarioService {

    public Usuario obtenerUsuarioAutenticado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
