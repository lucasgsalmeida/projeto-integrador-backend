package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.departamento.Departamento;
import me.lucasgsalmeida.gestao10x.model.domain.departamento.DepartamentoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    @Query("SELECT departamento FROM Departamento departamento WHERE departamento.id = :id and departamento.idEscritorio = :idEscritorio")
    Departamento findDepartamento(@Param("id") Long id, @Param("idEscritorio") Long idEscritorio);

    @Query("SELECT departamento FROM Departamento departamento WHERE departamento.idEscritorio = :idEscritorio")
    List<DepartamentoResponseDTO> findDepartamentoByEscritorio(@Param("idEscritorio") Long idEscritorio);

}
