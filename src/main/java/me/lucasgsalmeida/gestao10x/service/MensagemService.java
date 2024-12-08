package me.lucasgsalmeida.gestao10x.service;

import me.lucasgsalmeida.gestao10x.infra.UsuarioStateCache;
import me.lucasgsalmeida.gestao10x.model.domain.chat.Mensagem;
import me.lucasgsalmeida.gestao10x.model.domain.chat.MensagemRequestDTO;
import me.lucasgsalmeida.gestao10x.model.domain.chat.MensagemResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.Usuario;
import me.lucasgsalmeida.gestao10x.model.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository repository;

    @Autowired
    private UsuarioStateCache usuarioStateCache;

    public ResponseEntity criarMensagem(MensagemRequestDTO data, UserDetails userDetails) {

        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null || data == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!(Objects.equals(data.idUsuarioRemetente(), user.getId()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Mensagem mensagem = new Mensagem(data);
        repository.save(mensagem);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity getMensagem(Long idRemetente, Long idDestinatario, UserDetails userDetails) {

        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!Objects.equals(idRemetente, user.getId()) && !Objects.equals(idDestinatario, user.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<MensagemResponseDTO> findMensagensEntreUsuarios = repository.findMensagensEntreUsuarios(idRemetente, idDestinatario);
        return ResponseEntity.ok(findMensagensEntreUsuarios);
    }

    public ResponseEntity getMensagemByUsuario(Long idUsuario, UserDetails userDetail) {

        Usuario user = usuarioStateCache.getUserState(userDetail.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (!Objects.equals(idUsuario, user.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<MensagemResponseDTO> findMensagemByUsuario = repository.findMensagensByUsuario(idUsuario);
        return ResponseEntity.ok(findMensagemByUsuario);
    }
}
