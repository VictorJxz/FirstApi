package com.tecnm.app.controller;

import com.tecnm.app.entity.Usuario;
import com.tecnm.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("usuarios/")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    ResponseEntity<?> listar(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id){
        Optional<Usuario> o = usuarioService.findById( id );
        if( o.isEmpty() ) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok( o.get() );
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario alumno){
        Usuario alumnoDb =  usuarioService.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body( alumnoDb );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Usuario alumno){
        Optional<Usuario> o = usuarioService.findById( id );
        if( o.isEmpty() ) {
            return ResponseEntity.notFound().build();
        }
        Usuario alumnoDb = o.get();
        alumnoDb.setNombre( alumno.getNombre() );
        alumnoDb.setApellido( alumno.getApellido() );
        alumnoDb.setEmail( alumno.getEmail() );
        return ResponseEntity.status(HttpStatus.CREATED).body( usuarioService.save( alumnoDb ) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  eliminar(@PathVariable Long id){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
