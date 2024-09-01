package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.Tarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.TarefaResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT tarefa FROM Tarefa tarefa WHERE tarefa.id = :id and tarefa.idEscritorio = :idEscritorio")
    Tarefa findTarefa(@Param("id") Long id, @Param("idEscritorio") Long idEscritorio);

    @Query("SELECT tarefa FROM Tarefa tarefa WHERE tarefa.idEscritorio = :idEscritorio")
    List<TarefaResponseDTO> findTarefaByEscritorio(@Param("idEscritorio") Long idEscritorio);

}
