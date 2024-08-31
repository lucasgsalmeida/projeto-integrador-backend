package me.lucasgsalmeida.gestao10x.model.domain.usuario;

public record UsuarioResponseDTO(Long id, Long idEscritorio, Long idDepartamento, String nome, String usuario, String senha, UserRole role) {

    public UsuarioResponseDTO(Usuario user) {
        this(user.getId(), user.getIdEscritorio(), user.getIdDepartamento(), user.getNome(), user.getUsuario(), user.getSenha(), user.getRole());
    }
}
