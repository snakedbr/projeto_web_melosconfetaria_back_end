package br.com.apiconfeitaria.projeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apiconfeitaria.projeto.model.Usuario;



public interface IUsuario extends JpaRepository<Usuario, Integer> {
    // Método para buscar usuário apenas pelo login
    Optional<Usuario> findByLogin(String login);
}