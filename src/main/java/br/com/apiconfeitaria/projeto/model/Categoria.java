package br.com.apiconfeitaria.projeto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="categoria")

public class Categoria {

	@Id // especifico que é a id da coluna
	@GeneratedValue(strategy= GenerationType.IDENTITY) // coloco para gerar automaticamente
	@Column(name= "id_categoria") // especifico a coluna
	private Integer id_categoria;
	
	@Column(name= "nome_categoria", length = 50, nullable = false) // especifico a coluna, com o tamanho e que não pode ser nulo
	private String nome_categoria;
	
	
	public Integer getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNome_categoria() {
		return nome_categoria;
	}
	public void setNome_categoria(String nome_categoria) {
		this.nome_categoria = nome_categoria;
	}
	
	
}
