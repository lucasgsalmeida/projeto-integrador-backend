package me.lucasgsalmeida.gestao10x.model.domain.usuario;

public record UsuarioAbstractDTO(Long id, Long idEscritorio, Long idDepartamento, String nome) {

    public UsuarioAbstractDTO(Usuario user) {
        this(user.getId(), user.getIdEscritorio(), user.getIdDepartamento(), user.getNome());
    }
}
