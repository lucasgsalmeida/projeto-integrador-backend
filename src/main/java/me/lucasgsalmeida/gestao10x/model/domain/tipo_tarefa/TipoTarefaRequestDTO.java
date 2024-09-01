package me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.departamento_ordem.DepartamentoOrdem;

import java.util.List;

public record TipoTarefaRequestDTO(List<DepartamentoOrdem> responsavelDepartamentoProjetos, String descricao) {
}
