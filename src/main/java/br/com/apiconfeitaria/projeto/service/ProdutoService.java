package br.com.apiconfeitaria.projeto.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import br.com.apiconfeitaria.projeto.model.Produto;
import br.com.apiconfeitaria.projeto.repository.IProduto;

//O Service é a camada onde a lógica de negócios é implementada. Ele é responsável por realizar as operações principais do sistema.

@Service
public class ProdutoService {

	private IProduto repository;

	public ProdutoService(IProduto repository) {
		this.repository = repository;
	}

	public List<Produto> listarProduto() {
		Iterable<Produto> produtos = repository.findAll();
		List<Produto> lista = StreamSupport.stream(produtos.spliterator(), false).collect(Collectors.toList());
		return lista;
	}
	
	public Produto criarProduto(Produto produto) {
		Produto produtoNovo = repository.save(produto);
		return produtoNovo;
	}
	
	public Produto editarProduto(Produto produto) {
		Produto produtoNovo = repository.save(produto);
		return produtoNovo;
	}
	
	public Boolean excluirProduto (Integer id_produto) {
		repository.deleteById(id_produto);
		return true;
	}
	
}
