package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.comentarios_sub_tarefa.ComentarioSubTarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentariosInterface extends JpaRepository<ComentarioSubTarefa, Long> {
}
