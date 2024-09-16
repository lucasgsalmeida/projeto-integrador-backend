package me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.comentarios_sub_tarefa.ComentarioSubTarefa;

import java.sql.Date;
import java.util.List;

public record SubTarefaResponseDTO(Long id, Long idEscritorio, Long idUsuario, Long idDepartamento, StatusTarefa statusTarefa, Date dataInicio, Date dataFim, List<ComentarioSubTarefa> comentarios) {

    public SubTarefaResponseDTO(SubTarefa subTarefa) {
        this(subTarefa.getId(), subTarefa.getIdEscritorio(), subTarefa.getIdUsuario(), subTarefa.getIdDepartamento(), subTarefa.getStatusTarefa(), subTarefa.getDataInicio(), subTarefa.getDataFim(), subTarefa.getComentarios());
    }
}
