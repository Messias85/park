package com.giovani.park.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giovani.park.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	// Método útil para verificar se um username já existe antes de cadastrar
    boolean existsByUsername(String username);

}
