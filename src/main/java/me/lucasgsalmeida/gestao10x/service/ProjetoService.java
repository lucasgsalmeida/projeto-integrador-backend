package me.lucasgsalmeida.gestao10x.service;

import jakarta.transaction.Transactional;
import me.lucasgsalmeida.gestao10x.infra.UsuarioStateCache;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.Projeto;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.ProjetoRequestDTO;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.ProjetoResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.Usuario;
import me.lucasgsalmeida.gestao10x.model.repository.ProjetoRepository;
import me.lucasgsalmeida.gestao10x.model.repository.RDPRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private UsuarioStateCache usuarioStateCache;

    @Autowired
    private RDPRepository rdpRepository;

    public ResponseEntity createProjeto(ProjetoRequestDTO data, UserDetails userDetails) {

        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (data == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Projeto projeto = new Projeto(data);
        projeto.setIdEscritorio(user.getIdEscritorio());

        System.out.println(data.dataInicio());

        rdpRepository.saveAll(data.rdp());
        repository.save(projeto);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity getProjetoById(Long id, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        Projeto projeto = repository.findProjetoById(id, user.getIdEscritorio());
        ProjetoResponseDTO dto = new ProjetoResponseDTO(projeto);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity getAllProjeto(UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<ProjetoResponseDTO> projetos = repository.findProjetoByEscritorio(user.getIdEscritorio());
        return ResponseEntity.ok(projetos);
    }

    @Transactional
    public ResponseEntity<?> updateProjeto(Long id, ProjetoRequestDTO data, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Projeto projeto = repository.findProjetoById(id, user.getIdEscritorio());

        if (projeto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        BeanUtils.copyProperties(data, projeto);
        BeanUtils.copyProperties(data.rdp(), projeto.getRdp());

        rdpRepository.saveAll(projeto.getRdp());
        repository.save(projeto);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteProjeto(Long id, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        Projeto projeto = repository.findProjetoById(id, user.getIdEscritorio());

        if (projeto == null) {
            return ResponseEntity.badRequest().build();
        }

        repository.delete(projeto);
        return ResponseEntity.ok().build();
    }

}
