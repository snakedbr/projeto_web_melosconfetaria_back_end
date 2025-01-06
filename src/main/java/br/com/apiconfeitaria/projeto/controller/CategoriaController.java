package br.com.apiconfeitaria.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiconfeitaria.projeto.model.Categoria;
import br.com.apiconfeitaria.projeto.repository.ICategoria;
import br.com.apiconfeitaria.projeto.service.CategoriaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	private ICategoria dao;
	private CategoriaService categoriaService;

	public CategoriaController (CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	// só para não usar um implements

	@GetMapping
	public ResponseEntity<List<Categoria>> listacategoria() {
		return ResponseEntity.status(200).body(categoriaService.listarCategoria());

	}

	@PostMapping // estou criando esse método para criar usuario mas preciso especificar o local
					// do Post
	public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
		return ResponseEntity.status(201).body(categoriaService.criarCategoria(categoria));

		// uso o postMan para testar o método
	}

	@PutMapping // método para editar
	public ResponseEntity<Categoria> criarProduto(@RequestBody Categoria categoria) {
		return ResponseEntity.status(200).body(categoriaService.editarCategoria(categoria));
	}

	@DeleteMapping("/{id_categoria}") // método para deletar
	public ResponseEntity<?> excluirCategoria(@PathVariable Integer id_categoria) {
		categoriaService.excluirCategoria(id_categoria);
		return ResponseEntity.status(204).build();
	}
}
