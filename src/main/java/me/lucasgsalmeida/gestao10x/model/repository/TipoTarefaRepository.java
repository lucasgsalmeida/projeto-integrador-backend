package me.lucasgsalmeida.gestao10x.model.repository;

import me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.TipoTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tipo_tarefa.TipoTarefaResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoTarefaRepository extends JpaRepository<TipoTarefa, Long> {

    @Query("SELECT tipoTarefa FROM TipoTarefa tipoTarefa WHERE tipoTarefa.id = :id and tipoTarefa.idEscritorio = :idEscritorio")
    TipoTarefa findTipoTarefa(@Param("id") Long id, @Param("idEscritorio") Long idEscritorio);

    @Query("SELECT tipoTarefa FROM TipoTarefa tipoTarefa WHERE tipoTarefa.idEscritorio = :idEscritorio")
    List<TipoTarefaResponseDTO> findTipoTarefaByEscritorio(@Param("idEscritorio") Long idEscritorio);

}
