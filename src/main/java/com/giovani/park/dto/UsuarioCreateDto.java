package com.giovani.park.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioCreateDto {

    
	@Email(message = "Formato do e-mail inválido! Ex: usuario@dominio.com", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	@NotBlank
    private String username;

     
	@NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 12, message = "A senha deve ter entre 6 e 12 caracteres")
    private String password;
 
    
    private String role;

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// Getters e Setters OBRIGATÓRIOS para o ModelMapper funcionar
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