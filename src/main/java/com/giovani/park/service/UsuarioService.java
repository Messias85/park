package com.giovani.park.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.giovani.park.exception.PasswordInvalidException;
import com.giovani.park.exception.UsernameUniqueViolationException;
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
			throw new UsernameUniqueViolationException(String.format("Username '%s' já está em uso.", usuario.getUsername()));
		}
		
		// Preenche campos de auditoria e papel padrão caso não informados
        usuario.setDataCriacao(LocalDateTime.now());
        if (usuario.getRole() == null) {
            usuario.setRole(Usuario.Role.ROLE_CLIENTE);
        }
        
         try {
           return usuarioRepository.save(usuario);
         }catch(org.springframework.dao.DataIntegrityViolationException ex) {
        	 throw new UsernameUniqueViolationException(
        			    String.format("Username %s já cadastrado", usuario.getUsername())
        			);
         }
		
	}

   // pesquisa por id
	@Transactional(readOnly = true)
	public Usuario buscarPorId(@PathVariable Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Usuario não encontrado") 
				);
	}
	

	@Transactional
	public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
		// TODO Auto-generated method stub
		
		if(!novaSenha.equals(confirmaSenha)) {
			throw new  PasswordInvalidException("Nova senha não confere  com confirmacao de senha.");
			
		}
		
		Usuario usuario = buscarPorId(id);
		if(!usuario.getPassword().equals(senhaAtual)) {
			throw new  PasswordInvalidException("Sua senha não confere.");
			
		}
		
		usuario.setPassword(novaSenha);
		return usuario;
	}
	

	// pesquisa por todos
		@Transactional(readOnly = true)
	public List<Usuario> buscarPorTodos() {
		// TODO Auto-generated method stub
		return  usuarioRepository.findAll();
	}
	
	
      //update
		@Transactional
	    public Usuario atualizar(Long id, Usuario dto) {
	        Usuario usuario = usuarioRepository.findById(id).
	        		orElseThrow(
	        				() -> new RuntimeException("Usuario não encontrado") 
	        				);

	        // Atualiza os valores com os dados recebidos no DTO
	        if (dto.getUsername() != null) {
	            usuario.setUsername(dto.getUsername());
	        }
	        if (dto.getPassword() != null) {
	            usuario.setPassword(dto.getPassword());
	        }
	        if (dto.getRole() != null) {
	            usuario.setRole(dto.getRole());
	        }

	        return usuario;
		}
	
	

}
