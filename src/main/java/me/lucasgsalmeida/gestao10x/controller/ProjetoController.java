package me.lucasgsalmeida.gestao10x.controller;

import me.lucasgsalmeida.gestao10x.model.domain.projeto.ProjetoRequestDTO;
import me.lucasgsalmeida.gestao10x.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService service;

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createProjeto(@RequestBody ProjetoRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.createProjeto(data, userDetails);
    }

    @GetMapping("/get")
    public ResponseEntity getProjetoById(@RequestParam(name="id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getProjetoById(id, userDetails);
    }

    @GetMapping("/get/all")
    public ResponseEntity getAllProjeto(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getAllProjeto(userDetails);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProjeto(@PathVariable Long id, @RequestBody ProjetoRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.updateProjeto(id, data, userDetails);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProjeto(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.deleteProjeto(id, userDetails);
    }


}
