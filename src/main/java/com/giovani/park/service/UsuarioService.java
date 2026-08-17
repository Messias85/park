package com.giovani.park.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.giovani.park.model.Usuario;
import com.giovani.park.repository.UsuarioRepository;


import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService {
	
	
	@Autowired
	UsuarioRepository usuarioRepository;

	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		// TODO Auto-generated method stub
		
		if(usuarioRepository.existsByUsername(usuario.getUsername())) {
			throw new RuntimeException("Username '" + usuario.getUsername() + "' já está em uso.");
		}
		
		// Preenche campos de auditoria e papel padrão caso não informados
        usuario.setDataCriacao(LocalDateTime.now());
        if (usuario.getRole() == null) {
            usuario.setRole(Usuario.Role.ROLE_CLIENTE);
        }

        return usuarioRepository.save(usuario);
		
	}

   // pesquisa por id
	@Transactional(readOnly = true)
	public Usuario buscarPorId(@PathVariable Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Usuario não encontrado") 
				);
	}
	

	@Transactional
	public Usuario editarSenha(Long id, String password) {
		// TODO Auto-generated method stub
		
		Usuario usuario = buscarPorId(id);
		usuario.setPassword(password);
		return usuario;
	}
	
	

	
	

}
