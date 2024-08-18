package me.lucasgsalmeida.gestao10x.model.domain.tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.PrioridadeTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;

import java.util.Date;

public record TarefaResponseDTO(Long id, Long idEscritorio, Long idProjeto, Long id_tipoTarefa, PrioridadeTarefa prioridadeTarefa, Date dataInicio, String descricao, StatusTarefa status) {

    public TarefaResponseDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getIdEscritorio(), tarefa.getIdProjeto(), tarefa.getId_tipoTarefa(), tarefa.getPrioridadeTarefa(), tarefa.getDataInicio(), tarefa.getDescricao(), tarefa.getStatus());
    }
}
