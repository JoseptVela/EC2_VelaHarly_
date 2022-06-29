package com.idat.Evaluacion02.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.Evaluacion02.dto.ProductosDTORequest;
import com.idat.Evaluacion02.dto.ProductosDTOResponse;
import com.idat.Evaluacion02.model.Productos;
import com.idat.Evaluacion02.repository.ProductosRepository;

@Service
public class ProductosServiceImpl implements ProductosService{
	
	@Autowired
	private ProductosRepository repository;

	@Override
	public void guardarProductos(ProductosDTORequest producto) {
		Productos p = new Productos();
		p.setNombreProducto(producto.getNombre());
		p.setDescripcion(p.getDescripcion());
		p.setPrecio(producto.getPrecioProducto());
		p.setStock(producto.getStockProducto());
		repository.save(p);
	}

	@Override
	public void actualizarProductos(ProductosDTORequest producto) {
		
		Productos p = new Productos();
		p.setIdProducto(producto.getId());
		p.setNombreProducto(producto.getNombre());
		p.setDescripcion(p.getDescripcion());
		p.setPrecio(producto.getPrecioProducto());
		p.setStock(producto.getStockProducto());

		repository.saveAndFlush(p);
	}

	@Override
	public void eliminarProductos(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<ProductosDTOResponse> listarProductos() {
		List<ProductosDTOResponse> listar = new ArrayList<>();
		
		ProductosDTOResponse dto = null;
		
		List<Productos> p = repository.findAll();
		
		for (Productos productos : p) {
			
			dto = new ProductosDTOResponse();
			
			dto.setDescripcionProducto(productos.getDescripcion());
			dto.setNombre(productos.getNombreProducto());
			dto.setPrecioProducto(productos.getPrecio());
			dto.setStockProducto(productos.getStock());
			dto.setId(productos.getIdProducto());
			
			listar.add(dto);
		}
		
		return listar;
	}

	@Override
	public ProductosDTOResponse obtenerProductosId(Integer id) {
		Productos productos = repository.findById(id).orElse(null);
		ProductosDTOResponse dto = new ProductosDTOResponse();
		
		dto.setDescripcionProducto(productos.getDescripcion());
		dto.setNombre(productos.getNombreProducto());
		dto.setPrecioProducto(productos.getPrecio());
		dto.setStockProducto(productos.getStock());
		dto.setId(productos.getIdProducto());
		
		return dto;
	}
	
	

}
