package br.com.apiconfeitaria.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiconfeitaria.projeto.DAO.ICategoria;
import br.com.apiconfeitaria.projeto.model.Categoria;

@RestController
@CrossOrigin("*")
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	private ICategoria dao;

	// só para não usar um implements

	@GetMapping
	public List<Categoria> listacategoria() {
		return (List<Categoria>) dao.findAll();

	}

	@PostMapping // estou criando esse método para criar usuario mas preciso especificar o local
								// do Post
	public Categoria criarCategoria(@RequestBody Categoria categoria) {
		Categoria categoriaNova = dao.save(categoria);
		return categoriaNova;

		// uso o postMan para testar o método
	}
	
	@PutMapping// método para editar 
	public Categoria criarProduto(@RequestBody Categoria categoria) {
		Categoria categoriaNova = dao.save(categoria);
		return categoriaNova;
	}
	
	@DeleteMapping ("/{id_categoria}") // método para deletar
	public Optional<Categoria> excluirCategoria (@PathVariable Integer id_categoria) {
		Optional<Categoria> categoria = dao.findById(id_categoria);
		dao.deleteById(id_categoria);
		return categoria;
	}
}
