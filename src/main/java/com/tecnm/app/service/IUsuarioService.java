package com.tecnm.app.service;

import com.tecnm.app.entity.Usuario;
import java.util.Optional;

public interface IUsuarioService {
    Iterable<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario alumno);
    void deleteById(Long id);
}
