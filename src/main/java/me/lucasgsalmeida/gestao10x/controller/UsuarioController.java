package me.lucasgsalmeida.gestao10x.controller;

import me.lucasgsalmeida.gestao10x.model.domain.usuario.UsuarioRequestDTO;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.UsuarioResponseDTO;
import me.lucasgsalmeida.gestao10x.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/new/admin")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity createUsuarioMaster(@RequestBody UsuarioRequestDTO data) {
        return service.createUsuarioMaster(data);
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createUsuario(@RequestBody UsuarioRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.createUsuario(data, userDetails);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated UsuarioResponseDTO data) {
        return service.login(data);
    }

    @GetMapping("/get/id")
    public ResponseEntity<?> getUsuarioAndCliente(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getUsuarioAndCliente(userDetails);
    }

    @PostMapping("/verify-token")
    public ResponseEntity<String> verifyToken(@RequestHeader("Authorization") String authorizationHeader) {
        return service.verifyToken(authorizationHeader);
    }

    @GetMapping("/get/all")
    public ResponseEntity getAllUsuario(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getAllUsuario(userDetails);
    }

}

