package br.com.apiconfeitaria.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.apiconfeitaria.projeto.model.Categoria;

public interface ICategoria extends JpaRepository<Categoria, Integer> {

}
