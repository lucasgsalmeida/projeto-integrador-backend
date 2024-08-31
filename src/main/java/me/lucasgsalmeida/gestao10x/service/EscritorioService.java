package me.lucasgsalmeida.gestao10x.service;

import me.lucasgsalmeida.gestao10x.infra.EscritorioStateCache;
import me.lucasgsalmeida.gestao10x.infra.UsuarioStateCache;
import me.lucasgsalmeida.gestao10x.model.domain.escritorio.Escritorio;
import me.lucasgsalmeida.gestao10x.model.domain.escritorio.EscritorioRequestDTO;
import me.lucasgsalmeida.gestao10x.model.domain.escritorio.EscritorioResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.Usuario;
import me.lucasgsalmeida.gestao10x.model.repository.EscritorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EscritorioService {

    @Autowired
    private EscritorioRepository repository;

    @Autowired
    private EscritorioStateCache escritorioStateCache;

    @Autowired
    private UsuarioStateCache usuarioStateCache;

    public ResponseEntity createEscritorio(EscritorioRequestDTO data) {

        if (data == null) {
            return ResponseEntity.badRequest().build();
        }

        Escritorio escritorio = new Escritorio(data);
        repository.save(escritorio);
        escritorioStateCache.saveEscritorioState(escritorio.getId(), escritorio);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<EscritorioResponseDTO> getEscritorioById(Long id, UserDetails userDetails) {

        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (user.getIdEscritorio() == null || !user.getIdEscritorio().equals(id)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Escritorio> optionalEscritorio = repository.findById(id);
        if (!optionalEscritorio.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Escritorio escritorio = optionalEscritorio.get();

        EscritorioResponseDTO dto = new EscritorioResponseDTO(escritorio);
        return ResponseEntity.ok(dto);
    }
}
