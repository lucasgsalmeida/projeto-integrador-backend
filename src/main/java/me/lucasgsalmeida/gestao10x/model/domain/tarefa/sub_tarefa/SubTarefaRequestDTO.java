package me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;

import java.util.Date;

public record SubTarefaRequestDTO(Long idTarefa, Long idProjeto, Long id_rdp, StatusTarefa statusTarefa, Date dataInicio, Date dataFim) {
}
