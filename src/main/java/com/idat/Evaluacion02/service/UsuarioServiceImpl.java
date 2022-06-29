package com.idat.Evaluacion02.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.Evaluacion02.dto.UsuarioDTORequest;
import com.idat.Evaluacion02.dto.UsuarioDTOResponse;
import com.idat.Evaluacion02.model.Usuario;
import com.idat.Evaluacion02.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	 @Autowired
	 private UsuarioRepository repository;

	@Override
	public void guardarUsuario(UsuarioDTORequest usuario) {
		
		Usuario p = new Usuario();
		p.setNombreUsuario(usuario.getNombre());
		p.setPassword(usuario.getPasswordUsuario());
		p.setRol(usuario.getRolUsuario());
		repository.save(p);
	}

	@Override
	public void actualizarUsuario(UsuarioDTORequest usuario) {
		Usuario p = new Usuario();
		p.setIdUsuario(usuario.getId());
		p.setNombreUsuario(usuario.getNombre());
		p.setPassword(usuario.getPasswordUsuario());
		p.setRol(usuario.getRolUsuario());
		repository.saveAndFlush(p);
	}

	@Override
	public void eliminarUsuario(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<UsuarioDTOResponse> listarUsuario() {
		List<UsuarioDTOResponse> listar = new ArrayList<>();
		
		UsuarioDTOResponse dto = null;
		
		List<Usuario> p = repository.findAll();
		
		for (Usuario usuario : p) {
			
			dto = new UsuarioDTOResponse();
			
			dto.setNombre(usuario.getNombreUsuario());
			dto.setPasswordUsuario(usuario.getPassword());
			dto.setRolUsuario(usuario.getRol());
			dto.setId(usuario.getIdUsuario());
			
			listar.add(dto);
		}
		
		return listar;
	}

	@Override
	public UsuarioDTOResponse obtenerUsuarioId(Integer id) {
		Usuario usuario = repository.findById(id).orElse(null);
		UsuarioDTOResponse dto = new UsuarioDTOResponse();
		
		dto.setNombre(usuario.getNombreUsuario());
		dto.setPasswordUsuario(usuario.getPassword());
		dto.setRolUsuario(usuario.getRol());
		dto.setId(usuario.getIdUsuario());
		
		return dto;
	}
	 
	 

}
