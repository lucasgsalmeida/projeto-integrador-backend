package me.lucasgsalmeida.gestao10x.model.domain.usuario;

public record UsuarioRequestDTO(Long idEscritorio, Long idDepartamento, String nome, String usuario, String senha, UserRole role) {
}
