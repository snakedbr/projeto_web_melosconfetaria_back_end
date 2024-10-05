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

import br.com.apiconfeitaria.projeto.DAO.IUsuario;
import br.com.apiconfeitaria.projeto.model.Usuario;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuario dao;

	// só para não usar um implements

	@GetMapping
	public List<Usuario> listausuarios() {
		return (List<Usuario>) dao.findAll();

	}

	@PostMapping // estou criando esse método para criar usuario mas preciso especificar o local
								// do Post
	public Usuario criarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioNovo = dao.save(usuario);
		return usuarioNovo;

		// uso o postMan para testar o método
	}

	@PutMapping // método para editar
	public Usuario editarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioNovo = dao.save(usuario);
		return usuarioNovo;
	}

	@DeleteMapping("/{id_usuario}") // método para deletar
	public Optional<Usuario> excluirUsuario(@PathVariable Integer id_usuario) {
		Optional<Usuario> usuario = dao.findById(id_usuario);
		dao.deleteById(id_usuario);
		return usuario;
	}

}
