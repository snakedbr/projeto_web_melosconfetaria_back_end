package br.com.apiconfeitaria.projeto.DAO;

import org.springframework.data.repository.CrudRepository;

import br.com.apiconfeitaria.projeto.model.Usuario;

public interface IUsuario extends CrudRepository<Usuario, Integer> {
	
	

}
