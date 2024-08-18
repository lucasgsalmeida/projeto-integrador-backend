package me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;

import java.util.Date;

@Entity
@Table(name = "SubTarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubTarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Escritorio", name = "id_escritorio", referencedColumnName = "id")
    private Long idEscritorio;

    @JoinColumn(table = "Tarefa", name = "id_tarefa", referencedColumnName = "id")
    private Long idTarefa;

    @JoinColumn(table = "Projeto", name = "id_projeto", referencedColumnName = "id")
    private Long idProjeto;

    @JoinColumn(table = "ResponsavelDepartamentoProjeto", name = "id_rdp", referencedColumnName = "id")
    private Long id_rdp;

    private StatusTarefa statusTarefa;

    private Date dataInicio;
    private Date dataFim;

    public SubTarefa(SubTarefaRequestDTO dto) {
        this.idTarefa = dto.idTarefa();
        this.idProjeto = dto.idProjeto();
        this.id_rdp = dto.id_rdp();
        this.statusTarefa = dto.statusTarefa();
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
    }

}
