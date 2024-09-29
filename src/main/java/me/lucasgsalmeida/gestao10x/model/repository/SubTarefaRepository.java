package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTarefaRepository extends JpaRepository<SubTarefa, Long> {
    List<SubTarefa> findByIdUsuario(Long idUsuario);
}
