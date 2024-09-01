package me.lucasgsalmeida.gestao10x.controller;


import me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.TipoTarefaRequestDTO;
import me.lucasgsalmeida.gestao10x.service.TipoTarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("modelo")
public class TipoTarefaController {

    @Autowired
    private TipoTarefaService service;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createTipoTarefa(@RequestBody TipoTarefaRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.createTipoTarefa(data, userDetails);
    }

    @GetMapping("/get/all")
    public ResponseEntity getAllTipoTarefa(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getAllTipoTarefa(userDetails);
    }

    @GetMapping("/get")
    public ResponseEntity getTipoTarefaById(@RequestParam(name="id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getTipoTarefaById(id, userDetails);
    }





}
