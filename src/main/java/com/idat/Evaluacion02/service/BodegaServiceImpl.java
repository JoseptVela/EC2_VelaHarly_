package com.idat.Evaluacion02.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.Evaluacion02.dto.BodegaDTORequest;
import com.idat.Evaluacion02.dto.BodegaDTOResponse;
import com.idat.Evaluacion02.model.Bodega;
import com.idat.Evaluacion02.repository.BodegaRepository;

@Service
public class BodegaServiceImpl implements BodegaService{
	
	@Autowired
	private BodegaRepository repository;

	@Override
	public void guardarBodega(BodegaDTORequest bodega) {
		Bodega p = new Bodega();
		p.setNombreBodega(bodega.getNombre());
		p.setDireccion(bodega.getDireccionBodega());
		repository.save(p);
	}

	@Override
	public void actualizarBodega(BodegaDTORequest bodega) {
		Bodega p = new Bodega();
		p.setIdBodega(bodega.getId());
		p.setNombreBodega(bodega.getNombre());
		p.setDireccion(bodega.getDireccionBodega());
		repository.saveAndFlush(p);
	}

	@Override
	public void eliminarBodega(Integer id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<BodegaDTOResponse> listarBodega() {
		List<BodegaDTOResponse> listar = new ArrayList<>();
		
		BodegaDTOResponse dto = null;
		
		List<Bodega> p = repository.findAll();
		
		for (Bodega bodegas : p) {
			
			dto = new BodegaDTOResponse();
			
			dto.setNombre(bodegas.getNombreBodega());
			dto.setDireccionBodega(bodegas.getDireccion());
			dto.setId(bodegas.getIdBodega());
			
			listar.add(dto);
		}
		
		return listar;
	}

	@Override
	public BodegaDTOResponse obtenerBodegaId(Integer id) {
		
		Bodega bodegas = repository.findById(id).orElse(null);
		BodegaDTOResponse dto = new BodegaDTOResponse();
		
		dto.setNombre(bodegas.getNombreBodega());
		dto.setDireccionBodega(bodegas.getDireccion());
		dto.setId(bodegas.getIdBodega());
		
		return dto;
	}

}
