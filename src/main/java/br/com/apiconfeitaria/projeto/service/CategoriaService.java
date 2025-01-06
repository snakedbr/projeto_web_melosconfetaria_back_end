package br.com.apiconfeitaria.projeto.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import br.com.apiconfeitaria.projeto.model.Categoria;
import br.com.apiconfeitaria.projeto.repository.ICategoria;

@Service
public class CategoriaService {

	private ICategoria repository;
	
	public CategoriaService (ICategoria repository) {
		this.repository = repository;
	}
	
	public List<Categoria> listarCategoria() {
		Iterable<Categoria> categorias = repository.findAll();
		List<Categoria> lista = StreamSupport.stream(categorias.spliterator(), false).collect(Collectors.toList());
		return lista;
	}
	
	public Categoria criarCategoria(Categoria categoria) {
		Categoria categoriaNova = repository.save(categoria);
		return categoriaNova;
	}
	
	public Categoria editarCategoria(Categoria categoria) {
		Categoria categoriaNova = repository.save(categoria);
		return categoriaNova;
	}
	
	public Boolean excluirCategoria (Integer id_categoria) {
		repository.deleteById(id_categoria);
		return true;
	}
}
