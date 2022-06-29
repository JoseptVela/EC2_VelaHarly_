package com.idat.Evaluacion02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.idat.Evaluacion02.dto.UsuarioDTORequest;
import com.idat.Evaluacion02.dto.UsuarioDTOResponse;
import com.idat.Evaluacion02.security.JWTUtil;
import com.idat.Evaluacion02.security.UserDetailService;
import com.idat.Evaluacion02.service.UsuarioService;

@RestController
@RequestMapping( path = "/usuarios/ec2")
public class UsuarioController {
	
	@Autowired
	private JWTUtil util;
	
	@Autowired
	private UserDetailService service_1;
	
	@Autowired
	private UsuarioService service;
	
	
	@RequestMapping(path = "/crearToken", method = RequestMethod.POST)
	public ResponseEntity<?> crearToken(@RequestBody UsuarioDTORequest request){
		
		UserDetails user = service_1.loadUserByUsername(request.getNombre());
		return ResponseEntity.ok(new UsuarioDTOResponse(util.generateToken(user.getUsername())));
		
	}
	
	@RequestMapping("/listar")
	public @ResponseBody ResponseEntity<List<UsuarioDTOResponse>>  listar() {
		
		return new ResponseEntity<List<UsuarioDTOResponse>>(service.listarUsuario(), HttpStatus.OK) ;
	}
	
	@RequestMapping( path = "/guardar", method = RequestMethod.POST)
	public ResponseEntity<Void> guardar(@RequestBody UsuarioDTORequest usuarios) {
		service.guardarUsuario(usuarios);
		return  new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}

	@RequestMapping( path = "/eliminar/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		UsuarioDTOResponse p =service.obtenerUsuarioId(id);
		if(p != null) {
			service.eliminarUsuario(id);
			return  new ResponseEntity<Void>(HttpStatus.OK);
		}
		return  new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping( path = "/actualizar", method = RequestMethod.PUT)
	public ResponseEntity<Void> actualizar(@RequestBody UsuarioDTORequest usuarios) {
		UsuarioDTOResponse p =service.obtenerUsuarioId(usuarios.getId());
		if(p != null) {
			service.actualizarUsuario(usuarios);
			return  new ResponseEntity<Void>(HttpStatus.OK);
		}
		return  new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping( path = "/listar/{id}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTOResponse> obtenerId(@PathVariable Integer id) {
		UsuarioDTOResponse p =service.obtenerUsuarioId(id);
		if(p != null) {
			return  new ResponseEntity<UsuarioDTOResponse>(service.obtenerUsuarioId(id),HttpStatus.OK);
		}
		return  new ResponseEntity<UsuarioDTOResponse>(HttpStatus.NOT_FOUND);
	}

}
