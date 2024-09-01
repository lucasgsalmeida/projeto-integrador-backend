package me.lucasgsalmeida.gestao10x.controller;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.TarefaRequestDTO;
import me.lucasgsalmeida.gestao10x.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @PostMapping("/create")
    public ResponseEntity createTarefa(@RequestBody TarefaRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {

        System.out.println("\n\n" + data + "\n\n");
        return service.createTarefa(data, userDetails);
    }

    @GetMapping("/get/all")
    public ResponseEntity getAllTarefa(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getAllTarefa(userDetails);
    }

    @GetMapping("/get")
    public ResponseEntity getTarefaById(@RequestParam(name="id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getTarefaById(id, userDetails);
    }

}
