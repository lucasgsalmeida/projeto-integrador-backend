package me.lucasgsalmeida.gestao10x.service;

import jakarta.transaction.Transactional;
import me.lucasgsalmeida.gestao10x.infra.UsuarioStateCache;
import me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.TipoTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.TipoTarefaRequestDTO;
import me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.TipoTarefaResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.departamento_ordem.DepartamentoOrdem;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.Usuario;
import me.lucasgsalmeida.gestao10x.model.repository.DepartamentoOrdemRepository;
import me.lucasgsalmeida.gestao10x.model.repository.TipoTarefaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoTarefaService {

    @Autowired
    private TipoTarefaRepository repository;

    @Autowired
    private UsuarioStateCache usuarioStateCache;

    @Autowired
    private DepartamentoOrdemRepository ordemRepository;

    public ResponseEntity createTipoTarefa(TipoTarefaRequestDTO data, UserDetails userDetails) {

        System.out.println("\n\n" + data.responsavelDepartamentoProjetos().toString() + "\n\n");

        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        if (data == null) {
            return ResponseEntity.badRequest().build();
        }

        TipoTarefa tipoTarefa = new TipoTarefa(data);
        tipoTarefa.setIdEscritorio(user.getIdEscritorio());

        List<DepartamentoOrdem> departamentoOrdems = data.responsavelDepartamentoProjetos();

        for (DepartamentoOrdem ordem : departamentoOrdems) {
            ordem.setIdEscritorio(user.getIdEscritorio());
        }

        ordemRepository.saveAll(departamentoOrdems);
        repository.save(tipoTarefa);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity getAllTipoTarefa(UserDetails userDetails) {

        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        List<TipoTarefaResponseDTO> listResponse = repository.findTipoTarefaByEscritorio(user.getIdEscritorio());
        return ResponseEntity.ok(listResponse);

    }

    public ResponseEntity getTipoTarefaById(Long id, UserDetails userDetails) {

        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        TipoTarefa tipoTarefa = repository.findTipoTarefa(id, user.getIdEscritorio());
        TipoTarefaResponseDTO dto = new TipoTarefaResponseDTO(tipoTarefa);
        return ResponseEntity.ok(dto);
    }

    @Transactional
    public ResponseEntity<?> updateTipoTarefa(Long id, TipoTarefaRequestDTO data, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        TipoTarefa tipoTarefa = repository.findTipoTarefa(id, user.getIdEscritorio());

        if (tipoTarefa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        BeanUtils.copyProperties(data, tipoTarefa);
        BeanUtils.copyProperties(data.responsavelDepartamentoProjetos(), tipoTarefa.getResponsavelDepartamentoProjetos());

        ordemRepository.saveAll(tipoTarefa.getResponsavelDepartamentoProjetos());
        repository.save(tipoTarefa);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteTipoTarefa(Long id, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        TipoTarefa tipoTarefa = repository.findTipoTarefa(id, user.getIdEscritorio());

        if (tipoTarefa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.delete(tipoTarefa);
        return ResponseEntity.ok().build();
    }


}
