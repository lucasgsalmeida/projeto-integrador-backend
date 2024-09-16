package me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.comentarios_sub_tarefa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ComentarioSubTarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ComentarioSubTarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Usuario", name = "id_usuario", referencedColumnName = "id")
    private Long idUsuario;

    @Column(length = 2000)
    private String mensagem;

    public ComentarioSubTarefa(CSTRequestDTO data) {
        this.mensagem = data.mensagem();
    }
}
