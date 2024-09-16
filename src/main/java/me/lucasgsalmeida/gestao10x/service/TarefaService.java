package me.lucasgsalmeida.gestao10x.service;

import me.lucasgsalmeida.gestao10x.infra.UsuarioStateCache;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.Tarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.TarefaResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.Tarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.TarefaRequestDTO;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.Usuario;
import me.lucasgsalmeida.gestao10x.model.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private UsuarioStateCache usuarioStateCache;

    @Autowired
    private SubTarefaService subTarefaService;


    public ResponseEntity createTarefa(TarefaRequestDTO data, UserDetails userDetails) {

        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (data == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Tarefa tarefa = new Tarefa(data);
        tarefa.setIdEscritorio(user.getIdEscritorio());

        System.out.print(tarefa.toString());

        List<SubTarefa> subTarefa = data.subTarefaList();
        for (SubTarefa sub : subTarefa) {
            sub.setIdEscritorio(user.getIdEscritorio());
            System.out.println(sub.toString());
        }
        subTarefaService.createSubTarefa(subTarefa);

        repository.save(tarefa);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity getTarefaById(Long id, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        Tarefa tarefa = repository.findTarefa(id, user.getIdEscritorio());
        TarefaResponseDTO dto = new TarefaResponseDTO(tarefa);
        return ResponseEntity.ok(dto);

    }

    public ResponseEntity getAllTarefa(UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        List<TarefaResponseDTO> tarefaResponseDTOList = repository.findTarefaByEscritorio(user.getIdEscritorio());
        return ResponseEntity.ok(tarefaResponseDTOList);
    }


}
