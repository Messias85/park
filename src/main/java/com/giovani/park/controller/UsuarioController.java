package com.giovani.park.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giovani.park.dto.UsuarioCreateDto;
import com.giovani.park.dto.UsuarioResponseDto;
import com.giovani.park.dto.UsuarioSenhaDto;
import com.giovani.park.dto.mapper.UsuarioMapper;
import com.giovani.park.model.Usuario;
import com.giovani.park.service.UsuarioService;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

	
	@Autowired
	UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	
	@PostMapping
	public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto dto) {
	    System.out.println("=== TESTE DE RECEBIMENTO ===");
	    System.out.println("DTO recebido é nulo? " + (dto == null));
	    if (dto != null) {
	        System.out.println("Username: " + dto.getUsername());
	        System.out.println("Password: " + dto.getPassword());
	    }
	    System.out.println("============================");

	    Usuario usuario = UsuarioMapper.toUsuario(dto);
	    Usuario usuarioSalvo = usuarioService.salvar(usuario);
	    return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(usuarioSalvo));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id ){
		
		Usuario user = usuarioService.buscarPorId(id);
		
		return ResponseEntity.ok(UsuarioMapper.toDto(user));
		
	}
	// UPDATE somente a senha
	
	@PatchMapping("/{id}")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id,@RequestBody UsuarioSenhaDto usuario ){
		
		Usuario user = usuarioService.editarSenha(id, usuario.getSenhaAtual(),usuario.getNovaSenha(),usuario.getConfirmaSenha());
		
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<UsuarioResponseDto>> getAll(){
		
		List<Usuario> user = usuarioService.buscarPorTodos();
		
		return ResponseEntity.ok(UsuarioMapper.toListDto(user));
		
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario dto) {
        Usuario usuarioAtualizado = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(usuarioAtualizado);
    }

	
	
	
	
	
}
