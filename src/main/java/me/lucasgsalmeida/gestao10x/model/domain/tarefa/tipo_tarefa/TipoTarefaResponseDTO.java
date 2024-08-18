package me.lucasgsalmeida.gestao10x.model.domain.tarefa.tipo_tarefa;

import me.lucasgsalmeida.gestao10x.model.domain.projeto.responsavel_departamento_projeto.ResponsavelDepartamentoProjeto;

import java.util.List;

public record TipoTarefaResponseDTO(Long id, Long idEscritorio, List<ResponsavelDepartamentoProjeto> responsavelDepartamentoProjetos, String descricao) {

    public TipoTarefaResponseDTO(TipoTarefa tarefa) {
        this(tarefa.getId(), tarefa.getIdEscritorio(), tarefa.getResponsavelDepartamentoProjetos(), tarefa.getDescricao());
    }
}
