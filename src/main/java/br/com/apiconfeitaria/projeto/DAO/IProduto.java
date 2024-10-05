package br.com.apiconfeitaria.projeto.DAO;

import org.springframework.data.repository.CrudRepository;

import br.com.apiconfeitaria.projeto.model.Produto;

public interface  IProduto extends CrudRepository<Produto, Integer>{

}
