package me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.enums.StatusTarefa;
import me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.comentarios_sub_tarefa.ComentarioSubTarefa;

import java.sql.Date;
import java.util.List;

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

    @JoinColumn(table = "Usuario", name = "id_usuario", referencedColumnName = "id")
    private Long idUsuario;

    @JoinColumn(table = "Departamento", name = "id_departamento", referencedColumnName = "id")
    private Long idDepartamento;

    private StatusTarefa statusTarefa;

    private Date dataInicio;
    private Date dataFim;

    @OneToMany
    private List<ComentarioSubTarefa> comentarios;



    public SubTarefa(SubTarefaRequestDTO dto) {
        this.idUsuario = dto.idUsuario();
        this.idDepartamento = dto.idDepartamento();
        this.statusTarefa = dto.statusTarefa();
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.comentarios = dto.comentarios();
    }

    @Override
    public String toString() {
        return "SubTarefa{" +
                "id=" + id +
                ", idEscritorio=" + idEscritorio +
                ", idUsuario=" + idUsuario +
                ", idDepartamento=" + idDepartamento +
                ", statusTarefa=" + statusTarefa +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }
}
