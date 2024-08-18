package me.lucasgsalmeida.gestao10x.model.domain.tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.PrioridadeTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;

import java.util.Date;

public record TarefaRequestDTO(Long idProjeto, Long id_tipoTarefa, PrioridadeTarefa prioridadeTarefa, Date dataInicio, String descricao, StatusTarefa status) {
}
