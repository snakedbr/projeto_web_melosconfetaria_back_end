package br.com.apiconfeitaria.projeto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.apiconfeitaria.projeto.model.Usuario;
import br.com.apiconfeitaria.projeto.repository.IUsuario;

@Service
public class UsuarioService {

    private final IUsuario repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuario repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Listar todos os usuários
    public List<Usuario> listarUsuario() {
        Iterable<Usuario> usuarios = repository.findAll();
        return StreamSupport.stream(usuarios.spliterator(), false).collect(Collectors.toList());
    }

    // Criar novo usuário
    public Usuario criarUsuario(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return repository.save(usuario);
    }

    // Editar usuário
    public Usuario editarUsuario(Usuario usuario) {
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
        }
        return repository.save(usuario);
    }

    // Excluir usuário
    public Boolean excluirUsuario(Integer id_usuario) {
        repository.deleteById(id_usuario);
        return true;
    }

 // Método para verificar as credenciais de login
    public Usuario verificarCredenciais(String login, String senha) {
        // Busca o usuário pelo login
        Optional<Usuario> usuario = repository.findByLogin(login);

        // Verifica se o usuário foi encontrado e se a senha corresponde ao hash
        if (usuario.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            // Verifica se a senha em texto plano corresponde ao hash armazenado
            if (encoder.matches(senha, usuario.get().getSenha())) {
                return usuario.get();  // Senha correta, retorna o usuário
            }
        }
        return null;  // Senha incorreta ou usuário não encontrado
    }
    
    
}