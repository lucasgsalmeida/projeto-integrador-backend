package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.chat.Mensagem;
import me.lucasgsalmeida.gestao10x.model.domain.chat.MensagemResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    List<MensagemResponseDTO> findByIdUsuarioRemetente(Long idUsuarioRemetente);
    List<MensagemResponseDTO> findByIdUsuarioDestinatario(Long idUsuarioDestinatario);

    @Query("SELECT m FROM Mensagem m WHERE m.idUsuarioRemetente = :idUsuario OR m.idUsuarioDestinatario = :idUsuario ORDER BY m.dataEnvio ASC")
    List<MensagemResponseDTO> findMensagensByUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT m FROM Mensagem m WHERE " +
            "(m.idUsuarioRemetente = :usuario1 AND m.idUsuarioDestinatario = :usuario2) OR " +
            "(m.idUsuarioRemetente = :usuario2 AND m.idUsuarioDestinatario = :usuario1) " +
            "ORDER BY m.dataEnvio ASC")
    List<MensagemResponseDTO> findMensagensEntreUsuarios(@Param("usuario1") Long usuario1,
                                                         @Param("usuario2") Long usuario2);

}
