package com.idat.Evaluacion02.service;

import java.util.List;

import com.idat.Evaluacion02.dto.ProductosDTORequest;
import com.idat.Evaluacion02.dto.ProductosDTOResponse;

public interface ProductosService {
	
	void guardarProductos(ProductosDTORequest producto);
	void actualizarProductos(ProductosDTORequest producto);
	void eliminarProductos(Integer id);
	List<ProductosDTOResponse> listarProductos();
	ProductosDTOResponse obtenerProductosId(Integer id);

}
