package me.lucasgsalmeida.gestao10x.controller;

import me.lucasgsalmeida.gestao10x.model.domain.usuario.UsuarioRequestDTO;
import me.lucasgsalmeida.gestao10x.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/new/admin")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity createUsuarioMaster(@RequestBody UsuarioRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.createUsuarioMaster(data, userDetails);
    }
}
