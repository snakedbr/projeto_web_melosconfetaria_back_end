package br.com.apiconfeitaria.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")

// @Entity significa que é uma tabela, e o @Table serve para especificar o nome da tabela



public class Usuario {
	
	@Id // especifico que é a id da coluna
	@GeneratedValue(strategy= GenerationType.IDENTITY) // coloco para gerar automaticamente
	@Column(name= "id_usuario") // especifico a coluna
	private Integer id_usuario;
	
	@Column(name= "login", length = 50, nullable = false) // especifico a coluna, com o tamanho e que não pode ser nulo
	private String login;
	
	@Column(name= "nome", length = 100, nullable = false) // especifico a coluna, com o tamanho e que não pode ser nulo
	private String nome;
	
	@Column(name= "senha", columnDefinition = "TEXT", nullable = false) // especifico a coluna, o tipo de senha e que não pode ser nulo
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
