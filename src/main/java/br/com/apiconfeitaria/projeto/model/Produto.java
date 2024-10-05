package br.com.apiconfeitaria.projeto.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="produto")

// @Entity significa que é uma tabela, e o @Table serve para especificar o nome da tabela
public class Produto {

	@Id // especifico que é a id da coluna
	@GeneratedValue(strategy= GenerationType.IDENTITY) // coloco para gerar automaticamente
	@Column(name= "id_produto") // especifico a coluna
	private Integer id_produto;
	
    @ManyToOne(fetch = FetchType.LAZY)  // Muitos produtos podem pertencer a uma categoria
    @JoinColumn(name = "id_categoria", nullable = true) // Especifica a coluna que é a chave estrangeira e que o valor pode ser nulo
    @JsonIgnore  // Ignora a serialização da categoria
	private Categoria id_categoria; // como se usa chave estrangeira, tenho que referenciar o objeto Categoria
    
	@Column(name= "preco_venda", precision = 10, scale = 10, nullable = false) // especifico a coluna, com o tamanho e que não pode ser nulo
	private BigDecimal preco_venda;
	
	@Column(name= "nome_produto", length = 100, nullable = false) // especifico a coluna, com o tamanho e que não pode ser nulo
	private String nome_produto;
	
	@Column(name= "descricao_produto",columnDefinition = "TEXT", nullable = true) // especifico a coluna, com o tamanho e que não pode ser nulo
	private String descricao_produto;
	
	@Column(name= "imagem_produto", nullable = true) // especifico a coluna, com o tamanho e que não pode ser nulo
	private String imagem_produto;


	public Integer getId_produto() {
		return id_produto;
	}


	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}


	public Categoria getId_categoria() {
		return id_categoria;
	}


	public void setId_categoria(Categoria id_categoria) {
		this.id_categoria = id_categoria;
	}


	public BigDecimal getPreco_venda() {
		return preco_venda;
	}


	public void setPreco_venda(BigDecimal preco_venda) {
		this.preco_venda = preco_venda;
	}


	public String getNome_produto() {
		return nome_produto;
	}


	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}


	public String getDescricao_produto() {
		return descricao_produto;
	}


	public void setDescricao_produto(String descricao_produto) {
		this.descricao_produto = descricao_produto;
	}


	public String getImagem_produto() {
		return imagem_produto;
	}


	public void setImagem_produto(String imagem_produto) {
		this.imagem_produto = imagem_produto;
	}
	
	
}
