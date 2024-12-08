package me.lucasgsalmeida.gestao10x.controller;

import me.lucasgsalmeida.gestao10x.model.domain.chat.MensagemRequestDTO;
import me.lucasgsalmeida.gestao10x.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mensagem")
public class MensagemController {

    @Autowired
    private MensagemService service;

    @PostMapping("/new")
    public ResponseEntity criarMensagem(@RequestBody MensagemRequestDTO data, @AuthenticationPrincipal UserDetails userDetails) {
        return service.criarMensagem(data, userDetails);
    }

    @GetMapping("/get")
    public ResponseEntity getMensagem(@RequestParam(name="remetente") Long idRemetente, @RequestParam(name="destinatario") Long idDestinatario, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getMensagem(idRemetente, idDestinatario, userDetails);
    }

    @GetMapping("/get/usuario")
    public ResponseEntity getMensagemByUsuario(@RequestParam(name="id") Long idUsuario, @AuthenticationPrincipal UserDetails userDetail) {
        return service.getMensagemByUsuario(idUsuario, userDetail);
    }

}
