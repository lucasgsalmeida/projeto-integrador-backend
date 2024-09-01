package me.lucasgsalmeida.gestao10x.controller;

import me.lucasgsalmeida.gestao10x.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;
}
