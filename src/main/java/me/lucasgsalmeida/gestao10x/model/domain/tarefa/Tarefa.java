package me.lucasgsalmeida.gestao10x.model.domain.tarefa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.PrioridadeTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.SubTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.comentarios_sub_tarefa.Comentario;

import java.sql.Date;
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

    @JoinColumn(table = "Usuario", name = "id_usuario", referencedColumnName = "id")
    private Long idUsuario;

    @JoinColumn(table = "TipoTarefa", name = "id_tipoTarefa", referencedColumnName = "id")
    private Long id_tipoTarefa;

    @OneToMany
    private List<SubTarefa> subTarefaList;

    private PrioridadeTarefa prioridadeTarefa;

    private Date dataInicio;

    @Column(length = 2000)
    private String descricao;

    @OneToMany
    private List<Comentario> comentarios;

    private StatusTarefa status;

    public Tarefa(TarefaRequestDTO dto) {
        this.idProjeto = dto.idProjeto();
        this.idUsuario = dto.idUsuario();
        this.id_tipoTarefa = dto.id_tipoTarefa();
        this.subTarefaList = dto.subTarefaList();
        this.prioridadeTarefa = dto.prioridadeTarefa();
        this.dataInicio = dto.dataInicio();
        this.descricao = dto.descricao();
        this.status = dto.status();
        this.comentarios = dto.comentarios();
        }

}
