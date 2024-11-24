package net.javaguides.Login.dto;


public class LoginDTO {
    private String username;
    private String password; // Considera cifrar las contraseñas.

    // Constructor sin argumentos (necesario para Jackson)
    public LoginDTO() {}

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
