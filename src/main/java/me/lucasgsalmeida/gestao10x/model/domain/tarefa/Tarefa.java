package me.lucasgsalmeida.gestao10x.model.domain.tarefa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.PrioridadeTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Escritorio", name = "id_escritorio", referencedColumnName = "id")
    private Long idEscritorio;

    @JoinColumn(table = "Projeto", name = "id_projeto", referencedColumnName = "id")
    private Long idProjeto;

    @JoinColumn(table = "TipoTarefa", name = "id_tipoTarefa", referencedColumnName = "id")
    private Long id_tipoTarefa;

    @OneToMany
    private List<SubTarefa> listSubTarefa;

    private PrioridadeTarefa prioridadeTarefa;

    private Date dataInicio;

    private String descricao;

    private StatusTarefa status;

    public Tarefa(TarefaRequestDTO dto) {
        this.idProjeto = dto.idProjeto();
        this.id_tipoTarefa = dto.id_tipoTarefa();
        this.prioridadeTarefa = dto.prioridadeTarefa();
        this.dataInicio = dto.dataInicio();
        this.descricao = dto.descricao();
        this.status = dto.status();
        }

}
