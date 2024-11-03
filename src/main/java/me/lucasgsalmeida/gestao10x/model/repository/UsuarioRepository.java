package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.usuario.Usuario;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.UsuarioAbstractDTO;
import me.lucasgsalmeida.gestao10x.model.domain.usuario.UsuarioResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByUsuario(String usuario);

    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.id = :id and usuario.idEscritorio = :idEscritorio")
    Usuario findUsuario(@Param("id") Long id, @Param("idEscritorio") Long idEscritorio);

    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.idEscritorio = :idEscritorio")
    List<Usuario> findUsuarioByEscritorio(@Param("idEscritorio") Long idEscritorio);

}
