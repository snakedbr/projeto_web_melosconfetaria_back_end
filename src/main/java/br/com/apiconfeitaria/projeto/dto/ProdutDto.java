package br.com.apiconfeitaria.projeto.dto;

import java.math.BigDecimal;

public class ProdutDto {

	private Integer id_produto;
	private Integer id_categoria;
	private BigDecimal preco_venda;
	private String nome_produto;
	private String descricao_produto;
	private String imagem_produto;
	
    public ProdutDto() {
    }


	public ProdutDto(Integer id_produto, Integer id_categoria, BigDecimal preco_venda, String nome_produto,
			String descricao_produto, String imagem_produto) {
		super();
		this.id_produto = id_produto;
		this.id_categoria = id_categoria;
		this.preco_venda = preco_venda;
		this.nome_produto = nome_produto;
		this.descricao_produto = descricao_produto;
		this.imagem_produto = imagem_produto;
	}

	public Integer getId_produto() {
		return id_produto;
	}

	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
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
