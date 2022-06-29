package com.idat.Evaluacion02.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.Evaluacion02.dto.ClienteDTORequest;
import com.idat.Evaluacion02.dto.ClienteDTOResponse;
import com.idat.Evaluacion02.model.Cliente;
import com.idat.Evaluacion02.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public void guardarCliente(ClienteDTORequest cliente) {
		
		Cliente p = new Cliente();
		p.setNombreCliente(cliente.getNombre());
		p.setDireccion(cliente.getDireccionCliente());
		p.setDni(cliente.getDniCliente());
		repository.save(p);
	}

	@Override
	public void actualizarCliente(ClienteDTORequest cliente) {
		Cliente p = new Cliente();
		p.setNombreCliente(cliente.getNombre());
		p.setDireccion(cliente.getDireccionCliente());
		p.setDni(cliente.getDniCliente());
		p.setIdClinte(cliente.getId());
		repository.saveAndFlush(p);
	}

	@Override
	public void eliminarCliente(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<ClienteDTOResponse> listarCliente() {
		List<ClienteDTOResponse> listar = new ArrayList<>();
		
		ClienteDTOResponse dto = null;
		
		List<Cliente> p = repository.findAll();
		
		for (Cliente clientes : p) {
			
			dto = new ClienteDTOResponse();
			
			dto.setNombre(clientes.getNombreCliente());
			dto.setDireccionCliente(clientes.getDireccion());
			dto.setDniCliente(clientes.getDni());
			dto.setId(clientes.getIdClinte());
			
			listar.add(dto);
		}
		
		return listar;
	}

	@Override
	public ClienteDTOResponse obtenerClienteId(Integer id) {
		
		Cliente clientes = repository.findById(id).orElse(null);
		ClienteDTOResponse dto = new ClienteDTOResponse();
		
		dto.setNombre(clientes.getNombreCliente());
		dto.setDireccionCliente(clientes.getDireccion());
		dto.setDniCliente(clientes.getDni());
		dto.setId(clientes.getIdClinte());
		
		return dto;
	}

	

}
