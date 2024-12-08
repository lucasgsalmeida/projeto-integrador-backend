package me.lucasgsalmeida.gestao10x.model.domain.chat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Mensagem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    private LocalDateTime dataEnvio;

    @JoinColumn(table = "Usuario", name = "id_usuario", referencedColumnName = "id")
    private Long idUsuarioRemetente;

    @JoinColumn(table = "Usuario", name = "id_usuario", referencedColumnName = "id")
    private Long idUsuarioDestinatario;

    public Mensagem(MensagemRequestDTO dto){
        this.texto = dto.texto();
        this.dataEnvio = dto.dataEnvio();
        this.idUsuarioRemetente = dto.idUsuarioRemetente();
        this.idUsuarioDestinatario = dto.idUsuarioDestinatario();
    }

}
