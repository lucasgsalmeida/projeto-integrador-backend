package me.lucasgsalmeida.gestao10x.model.domain.departamento;

public record DepartamentoResponseDTO(Long id, Long idEscritorio, Long idUsuario, String nome) {

    public DepartamentoResponseDTO(Departamento departamento){
        this(departamento.getId(), departamento.getIdEscritorio(), departamento.getIdUsuario(), departamento.getNome());
    }
}
