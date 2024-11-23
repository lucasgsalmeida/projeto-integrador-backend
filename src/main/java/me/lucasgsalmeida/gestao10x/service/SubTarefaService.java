package me.lucasgsalmeida.gestao10x.service;


import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;
import me.lucasgsalmeida.gestao10x.model.repository.ComentarioRepository;
import me.lucasgsalmeida.gestao10x.model.repository.SubTarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTarefaService {

    @Autowired
    private SubTarefaRepository repository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public ResponseEntity createSubTarefa(List<SubTarefa> subTarefa) {
        for (SubTarefa sub : subTarefa) {
            if (sub.getComentarios() != null) {
                comentarioRepository.saveAll(sub.getComentarios());
            }
        }

        repository.saveAll(subTarefa);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity updateSubTarefa(List<SubTarefa> subTarefa) {
        for (SubTarefa sub : subTarefa) {
            if (sub.getComentarios() != null) {
                comentarioRepository.saveAll(sub.getComentarios());
            }
        }

        repository.saveAll(subTarefa);
        return ResponseEntity.ok().build();
    }


}
