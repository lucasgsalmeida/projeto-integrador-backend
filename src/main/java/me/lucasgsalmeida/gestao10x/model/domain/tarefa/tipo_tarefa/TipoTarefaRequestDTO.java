package me.lucasgsalmeida.gestao10x.model.domain.tarefa.tipo_tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.projeto.responsavel_departamento_projeto.ResponsavelDepartamentoProjeto;

import java.util.List;

public record TipoTarefaRequestDTO(List<ResponsavelDepartamentoProjeto> responsavelDepartamentoProjetos, String descricao) {
}
