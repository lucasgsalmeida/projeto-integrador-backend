package me.lucasgsalmeida.gestao10x.service;

import me.lucasgsalmeida.gestao10x.infra.UsuarioStateCache;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.Projeto;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.ProjetoRequestDTO;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.Tarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.TarefaResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.TarefaRequestDTO;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.Usuario;
import me.lucasgsalmeida.gestao10x.model.repository.ComentarioRepository;
import me.lucasgsalmeida.gestao10x.model.repository.SubTarefaRepository;
import me.lucasgsalmeida.gestao10x.model.repository.TarefaRepository;
import me.lucasgsalmeida.gestao10x.model.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private UsuarioStateCache usuarioStateCache;

    @Autowired
    private SubTarefaService subTarefaService;

    @Autowired
    private SubTarefaRepository subTarefaRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


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

        if (tarefa.getComentarios() != null) {
            comentarioRepository.saveAll(tarefa.getComentarios());
        }

        List<SubTarefa> subTarefa = data.subTarefaList();
        for (SubTarefa sub : subTarefa) {
            sub.setIdEscritorio(user.getIdEscritorio());
            System.out.println(sub.toString());
        }
        subTarefaService.createSubTarefa(subTarefa);

        repository.save(tarefa);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity updateTarefa(Long id, TarefaRequestDTO data, UserDetails userDetails) {

        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (data == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Tarefa tarefa = repository.findTarefa(id, user.getIdEscritorio());

        if (tarefa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        BeanUtils.copyProperties(data, tarefa);
        BeanUtils.copyProperties(data.subTarefaList(), tarefa.getSubTarefaList());

        if (tarefa.getComentarios() != null) {
            comentarioRepository.saveAll(tarefa.getComentarios());
        }

        List<SubTarefa> subTarefa = data.subTarefaList();
        for (SubTarefa sub : subTarefa) {
            sub.setIdEscritorio(user.getIdEscritorio());
        }
        subTarefaService.updateSubTarefa(subTarefa);
        repository.save(tarefa);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity<List<TarefaResponseDTO>> findTarefasAbertas(Long idUsuario, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        Usuario userDestino = usuarioRepository.findUsuario(idUsuario, user.getIdEscritorio());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (userDestino != null) {
            if (!Objects.equals(userDestino.getIdEscritorio(), user.getIdEscritorio())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }

        List<SubTarefa> subTarefas = subTarefaRepository.findByIdUsuario(idUsuario);
        Set<TarefaResponseDTO> tarefaReturn = new HashSet<>();

        for (SubTarefa sub : subTarefas) {
            TarefaResponseDTO tarefa = repository.findTarefaByUsuarioAbertas(sub);
            if (tarefa != null) {
                tarefaReturn.add(tarefa);
            }
        }

        return ResponseEntity.ok(new ArrayList<>(tarefaReturn)); // Converte de volta para List
    }

    public ResponseEntity<List<TarefaResponseDTO>> findTarefasFechadas(Long idUsuario, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        Usuario userDestino = usuarioRepository.findUsuario(idUsuario, user.getIdEscritorio());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (userDestino != null) {
            if (!Objects.equals(userDestino.getIdEscritorio(), user.getIdEscritorio())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }

        List<SubTarefa> subTarefas = subTarefaRepository.findByIdUsuario(idUsuario);
        Set<TarefaResponseDTO> tarefaReturn = new HashSet<>();

        for (SubTarefa sub : subTarefas) {
            TarefaResponseDTO tarefa = repository.findTarefaByUsuarioFechadas(sub);
            if (tarefa != null) {
                tarefaReturn.add(tarefa);
            }
        }

        return ResponseEntity.ok(new ArrayList<>(tarefaReturn)); // Converte de volta para List
    }

    public ResponseEntity<List<TarefaResponseDTO>> findTarefasByUsuarioByAprovacao(Long idUsuario, UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        Usuario userDestino = usuarioRepository.findUsuario(idUsuario, user.getIdEscritorio());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (userDestino != null) {
            if (!Objects.equals(userDestino.getIdEscritorio(), user.getIdEscritorio())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }

        List<SubTarefa> subTarefas = subTarefaRepository.findByStatus(idUsuario);
        Set<TarefaResponseDTO> tarefaReturn = new HashSet<>();

        for (SubTarefa sub : subTarefas) {
            TarefaResponseDTO tarefa = repository.findTarefaByUsuario(sub);
            if (tarefa != null) {
                tarefaReturn.add(tarefa);
            }
        }
        return ResponseEntity.ok(new ArrayList<>(tarefaReturn));
    }

    public ResponseEntity getAllTarefa(UserDetails userDetails) {
        Usuario user = usuarioStateCache.getUserState(userDetails.getUsername());
        List<TarefaResponseDTO> tarefaResponseDTOList = repository.findTarefaByEscritorio(user.getIdEscritorio());
        return ResponseEntity.ok(tarefaResponseDTOList);
    }


}
