package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
