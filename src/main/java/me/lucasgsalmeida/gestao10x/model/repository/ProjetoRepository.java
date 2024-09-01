package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.projeto.Projeto;
import me.lucasgsalmeida.gestao10x.model.domain.projeto.ProjetoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT projeto FROM Projeto projeto WHERE projeto.id = :id and projeto.idEscritorio = :idEscritorio")
    Projeto findProjetoById(@Param("id") Long id, @Param("idEscritorio") Long idEscritorio);

    @Query("SELECT projeto FROM Projeto projeto WHERE projeto.idEscritorio = :idEscritorio")
    List<ProjetoResponseDTO> findProjetoByEscritorio(@Param("idEscritorio") Long idEscritorio);

}
