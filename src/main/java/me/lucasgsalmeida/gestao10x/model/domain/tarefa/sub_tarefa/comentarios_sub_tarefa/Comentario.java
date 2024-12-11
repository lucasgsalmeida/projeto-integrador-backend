package me.lucasgsalmeida.gestao10x.model.domain.tarefa.sub_tarefa.comentarios_sub_tarefa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Comentario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Usuario", name = "id_usuario", referencedColumnName = "id")
    private Long idUsuario;

    @Column(length = 6000)
    private String mensagem;

    public Comentario(CSTRequestDTO data) {
        this.idUsuario = data.idUsuario();
        this.mensagem = data.mensagem();
    }
}
