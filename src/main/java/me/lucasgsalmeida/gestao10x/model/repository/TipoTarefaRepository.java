package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.tipo_tarefa.TipoTarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoTarefaRepository extends JpaRepository<TipoTarefa, Long> {
}
