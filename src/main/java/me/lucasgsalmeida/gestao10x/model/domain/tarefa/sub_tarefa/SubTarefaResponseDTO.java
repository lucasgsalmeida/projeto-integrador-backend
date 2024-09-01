package me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;

import java.util.Date;

public record SubTarefaResponseDTO(Long id, Long idEscritorio, Long idTarefa, Long idProjeto, Long idUsuario, Long idDepartamento, StatusTarefa statusTarefa, Date dataInicio, Date dataFim) {

    public SubTarefaResponseDTO(SubTarefa subTarefa) {
        this(subTarefa.getId(), subTarefa.getIdEscritorio(), subTarefa.getIdTarefa(), subTarefa.getIdProjeto(), subTarefa.getIdUsuario(), subTarefa.getIdDepartamento(), subTarefa.getStatusTarefa(), subTarefa.getDataInicio(), subTarefa.getDataFim());
    }
}
