package me.lucasgsalmeida.gestao10x.controller;


import me.lucasgsalmeida.gestao10x.model.domain.escritorio.EscritorioRequestDTO;
import me.lucasgsalmeida.gestao10x.service.EscritorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("escritorio")
public class EscritorioController {

    @Autowired
    private EscritorioService service;

    @PostMapping("/new")
    @PreAuthorize("hasRole('MASTER')")
    public ResponseEntity createEscritorio(@RequestBody EscritorioRequestDTO data) {
        return service.createEscritorio(data);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getEscritorioById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getEscritorioById(id, userDetails);
    }
}
