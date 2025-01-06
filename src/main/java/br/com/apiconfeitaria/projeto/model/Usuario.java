package br.com.apiconfeitaria.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="usuario")


// @Entity significa que é uma tabela, e o @Table serve para especificar o nome da tabela



public class Usuario {
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY) 
    @Column(name= "id_usuario") 
    private Integer id_usuario;

    @NotBlank(message = "O login não pode estar em branco")
    @Column(name= "login", length = 50, nullable = false) 
    private String login;

    @NotBlank(message = "O nome não pode estar em branco")
    @Column(name= "nome", length = 100, nullable = false) 
    private String nome;

    @NotBlank(message = "A senha não pode estar em branco")
    @Column(name= "senha", columnDefinition = "TEXT", nullable = false) 
    private String senha;
	
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
