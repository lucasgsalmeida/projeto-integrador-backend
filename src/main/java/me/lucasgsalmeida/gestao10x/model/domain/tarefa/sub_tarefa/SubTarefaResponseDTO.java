package me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;

import java.util.Date;

public record SubTarefaResponseDTO(Long id, Long idEscritorio, Long idTarefa, Long idProjeto, Long id_rdp, StatusTarefa statusTarefa, Date dataInicio, Date dataFim) {

    public SubTarefaResponseDTO(SubTarefa subTarefa) {
        this(subTarefa.getId(), subTarefa.getIdEscritorio(), subTarefa.getIdTarefa(), subTarefa.getIdProjeto(), subTarefa.getId_rdp(), subTarefa.getStatusTarefa(), subTarefa.getDataInicio(), subTarefa.getDataFim());
    }
}