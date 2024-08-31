package me.lucasgsalmeida.gestao10x.model.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(table = "Escritorio", name = "id_escritorio", referencedColumnName = "id")
    private Long idEscritorio;

    @JoinColumn(table = "Departamento", name = "id_departamento", referencedColumnName = "id")
    private Long idDepartamento;

    private String nome;

    private String usuario;

    private String senha;

    private UserRole role;

    public Usuario(UsuarioRequestDTO dto) {
        this.idEscritorio = dto.idEscritorio();
        this.idDepartamento = dto.idDepartamento();
        this.nome = dto.nome();
        this.usuario = dto.usuario();
        this.senha = dto.senha();
        this.role = dto.role();
    }

    public Usuario(Long idEscritorio, String nome, String usuario, String senha, UserRole role) {
        this.idEscritorio = idEscritorio;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.USER) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }

        if (this.role == UserRole.MASTER) {
            return List.of(new SimpleGrantedAuthority("ROLE_MASTER"), new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
