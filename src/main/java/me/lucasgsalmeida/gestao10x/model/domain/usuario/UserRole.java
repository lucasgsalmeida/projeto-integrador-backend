package me.lucasgsalmeida.gestao10x.model.domain.usuario;

public enum UserRole {

    MASTER("master"),
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return this.role;
    }


}