package br.com.apiconfeitaria.projeto.DAO;

import org.springframework.data.repository.CrudRepository;

import br.com.apiconfeitaria.projeto.model.Categoria;

public interface ICategoria extends CrudRepository<Categoria, Integer> {

}
