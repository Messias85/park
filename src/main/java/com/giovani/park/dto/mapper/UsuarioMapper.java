package com.giovani.park.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.giovani.park.dto.UsuarioCreateDto;
import com.giovani.park.dto.UsuarioResponseDto;
import com.giovani.park.model.Usuario;

public class UsuarioMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        if (createDto == null) {
            return null;
        }
        return mapper.map(createDto, Usuario.class);
    }
    
    public static UsuarioResponseDto toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioResponseDto dto = mapper.map(usuario, UsuarioResponseDto.class);

        if (usuario.getRole() != null) {
            String role = usuario.getRole().name();
            if (role.startsWith("ROLE_")) {
                role = role.substring("ROLE_".length());
            }
            dto.setRole(role);
        }

        return dto;
    }
    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios){
    	return usuarios.stream().map(user ->toDto(user)).collect(Collectors.toList());
    }
}