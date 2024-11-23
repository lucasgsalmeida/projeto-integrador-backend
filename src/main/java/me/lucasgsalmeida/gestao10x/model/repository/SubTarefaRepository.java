package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.TarefaResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubTarefaRepository extends JpaRepository<SubTarefa, Long> {
    List<SubTarefa> findByIdUsuario(Long idUsuario);

    @Query("SELECT t FROM SubTarefa t WHERE t.idUsuario = :usuario AND t.statusTarefa = me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa.APROVACAO")
    List<SubTarefa> findByStatus(@Param("usuario") Long idUsuario);

}
