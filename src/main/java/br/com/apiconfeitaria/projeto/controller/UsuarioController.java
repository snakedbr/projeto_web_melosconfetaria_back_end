package br.com.apiconfeitaria.projeto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiconfeitaria.projeto.model.Usuario;
import br.com.apiconfeitaria.projeto.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Método para listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> listausuarios() {
        return ResponseEntity.status(200).body(usuarioService.listarUsuario());
    }

    // Método para criar um novo usuário
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(usuarioService.criarUsuario(usuario));
    }

    // Método para editar um usuário existente
    @PutMapping
    public ResponseEntity<Usuario> editarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(200).body(usuarioService.editarUsuario(usuario));
    }

    // Método para excluir um usuário pelo ID
    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Integer id_usuario) {
        usuarioService.excluirUsuario(id_usuario);
        return ResponseEntity.status(204).build();
    }

    // Método GET para verificar login e senha do usuário
    @GetMapping("/verificar")
    public ResponseEntity<?> verificarUsuario(
            @RequestParam String login, 
            @RequestParam String senha) {
        Usuario usuario = usuarioService.verificarCredenciais(login, senha);
        if (usuario != null) {
            return ResponseEntity.ok(usuario); // Login e senha corretos, retorna o usuário
        } else {
            return ResponseEntity.status(401).body("Login ou senha inválidos"); // Login ou senha incorretos
        }
    }
} 