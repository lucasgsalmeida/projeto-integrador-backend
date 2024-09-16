package me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.departamento_ordem;

public record DOResponseDTO(Long id, Long idEscritorio, Long idDepartamento, int ordem, int qtdDias) {

    public DOResponseDTO(DepartamentoOrdem data) {
        this(data.getId(), data.getIdEscritorio(), data.getIdDepartamento(), data.getOrdem(), data.getQtdDias());
    }
}
