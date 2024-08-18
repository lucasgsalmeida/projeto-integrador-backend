package me.lucasgsalmeida.gestao10x.model.domain.projeto.responsavel_departamento_projeto;

public record RDPResponseDTO(Long id, Long idEscritorio, Long idDepartamento, Long idUsuario) {

    public RDPResponseDTO(ResponsavelDepartamentoProjeto rdp) {
        this(rdp.getId(), rdp.getIdEscritorio(), rdp.getIdDepartamento(), rdp.getIdUsuario());
    }
}
