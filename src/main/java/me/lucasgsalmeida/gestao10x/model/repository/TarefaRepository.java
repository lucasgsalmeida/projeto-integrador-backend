package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.tarefa.Tarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.TarefaResponseDTO;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT tarefa FROM Tarefa tarefa WHERE tarefa.id = :id and tarefa.idEscritorio = :idEscritorio")
    Tarefa findTarefa(@Param("id") Long id, @Param("idEscritorio") Long idEscritorio);

    @Query("SELECT tarefa FROM Tarefa tarefa WHERE tarefa.idEscritorio = :idEscritorio and tarefa.status != me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa.CONCLUIDO")
    List<TarefaResponseDTO> findTarefaByEscritorioAbertas(@Param("idEscritorio") Long idEscritorio);

    @Query("SELECT tarefa FROM Tarefa tarefa WHERE tarefa.idEscritorio = :idEscritorio and tarefa.status = me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa.CONCLUIDO")
    List<TarefaResponseDTO> findTarefaByEscritorioFechadas(@Param("idEscritorio") Long idEscritorio);

    @Query("SELECT t FROM Tarefa t JOIN t.subTarefaList st WHERE st = :subTarefa and t.status != me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa.CONCLUIDO")
    TarefaResponseDTO findTarefaByUsuarioAbertas(@Param("subTarefa") SubTarefa subTarefa);

    @Query("SELECT t FROM Tarefa t JOIN t.subTarefaList st " +
            "WHERE t.idUsuario = :idUsuario " +
            "AND st.statusTarefa = me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa.APROVACAO and t.status != me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa.CONCLUIDO")
    List<TarefaResponseDTO> findTarefasComSubTarefaEmAprovacao(@Param("idUsuario") Long idUsuario);

    @Query("SELECT t FROM Tarefa t JOIN t.subTarefaList st WHERE st = :subTarefa")
    TarefaResponseDTO findTarefaByUsuario(@Param("subTarefa") SubTarefa subTarefa);



}
