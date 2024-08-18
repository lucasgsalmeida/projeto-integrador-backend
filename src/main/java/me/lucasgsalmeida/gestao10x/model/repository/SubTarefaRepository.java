package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTarefaRepository extends JpaRepository<SubTarefa, Long> {
}
