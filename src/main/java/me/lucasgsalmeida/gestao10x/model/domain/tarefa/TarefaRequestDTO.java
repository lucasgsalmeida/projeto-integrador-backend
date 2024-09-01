package me.lucasgsalmeida.gestao10x.model.domain.tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.PrioridadeTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;

import java.util.Date;
import java.util.List;

public record TarefaRequestDTO(Long idProjeto, Long id_tipoTarefa, List<SubTarefa> subTarefaList, PrioridadeTarefa prioridadeTarefa, Date dataInicio, String descricao, StatusTarefa status) {
}
