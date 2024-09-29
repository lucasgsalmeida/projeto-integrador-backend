package me.lucasgsalmeida.gestao10x.model.domain.tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.PrioridadeTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.comentarios_sub_tarefa.Comentario;

import java.sql.Date;
import java.util.List;

public record TarefaResponseDTO(Long id, Long idEscritorio, Long idProjeto, Long id_tipoTarefa, List<SubTarefa> subTarefaList, PrioridadeTarefa prioridadeTarefa, Date dataInicio, String descricao, StatusTarefa status, List<Comentario> comentarios) {

    public TarefaResponseDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getIdEscritorio(), tarefa.getIdProjeto(), tarefa.getId_tipoTarefa(), tarefa.getSubTarefaList(), tarefa.getPrioridadeTarefa(), tarefa.getDataInicio(), tarefa.getDescricao(), tarefa.getStatus(), tarefa.getComentarios());
    }
}
