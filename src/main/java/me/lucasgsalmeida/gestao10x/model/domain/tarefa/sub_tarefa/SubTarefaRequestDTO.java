package me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.comentarios_sub_tarefa.Comentario;

import java.sql.Date;
import java.util.List;

public record SubTarefaRequestDTO(Long idUsuario, Long idDepartamento, StatusTarefa statusTarefa, Date dataInicio, Date dataFim, List<Comentario> comentarios) {
}
