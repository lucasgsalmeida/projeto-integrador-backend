package me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.departamento_ordem.DepartamentoOrdem;

import java.util.List;

public record TipoTarefaResponseDTO(Long id, Long idEscritorio, String nome, List<DepartamentoOrdem> responsavelDepartamentoProjetos, String descricao) {

    public TipoTarefaResponseDTO(TipoTarefa tarefa) {
        this(tarefa.getId(), tarefa.getIdEscritorio(), tarefa.getNome(), tarefa.getResponsavelDepartamentoProjetos(), tarefa.getDescricao());
    }
}
