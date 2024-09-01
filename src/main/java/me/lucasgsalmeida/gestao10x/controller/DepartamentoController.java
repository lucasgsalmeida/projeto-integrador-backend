package me.lucasgsalmeida.gestao10x.controller;

import me.lucasgsalmeida.gestao10x.model.domain.departamento.DepartamentoRequestDTO;
import me.lucasgsalmeida.gestao10x.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createDepartamento(@RequestBody DepartamentoRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.createDepartamento(data, userDetails);
    }

    @GetMapping("/get")
    public ResponseEntity getDepartamentoById(@RequestParam(name="id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getDepartamentoById(id, userDetails);
    }

    @GetMapping("/get/all")
    public ResponseEntity getAllDepartamento(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getAllDepartamento(userDetails);
    }


}
