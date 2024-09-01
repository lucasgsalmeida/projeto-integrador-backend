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

    @JoinColumn(table = "Usuario", name = "id_usuario", referencedColumnName = "id")
    private Long idUsuario;

    @JoinColumn(table = "Departamento", name = "id_departamento", referencedColumnName = "id")
    private Long idDepartamento;

    private StatusTarefa statusTarefa;

    private Date dataInicio;
    private Date dataFim;

    public SubTarefa(SubTarefaRequestDTO dto) {
        this.idProjeto = dto.idProjeto();
        this.idUsuario = dto.idUsuario();
        this.idDepartamento = dto.idDepartamento();
        this.statusTarefa = dto.statusTarefa();
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
    }

}
