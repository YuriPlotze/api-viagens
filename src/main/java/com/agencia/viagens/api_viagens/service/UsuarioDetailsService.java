package com.agencia.viagens.api_viagens.service;

import com.agencia.viagens.api_viagens.model.Usuario;
import com.agencia.viagens.api_viagens.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNome(username);
        
        if (optionalUsuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        Usuario usuario = optionalUsuario.get();
        return User.builder()
            .username(usuario.getNome())
            .password(usuario.getSenha()) // Certifique-se de que a senha está em formato hash no banco de dados
            .roles("USER")
            .build();
    }
}
