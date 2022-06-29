package com.idat.Evaluacion02.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Productos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	private String nombreProducto;
	
	private String descripcion;
	private Double precio;
	private Integer stock;
	
	@OneToMany(mappedBy = "productos")
	private List<Bodega> bodegas = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "productos_cliente",
			joinColumns = @JoinColumn(
					name="id_cliente", 
					nullable = false, 
					unique = true, 
					foreignKey = @javax.persistence.ForeignKey(foreignKeyDefinition = "foreign key (id_cliente) references Cliente(id_cliente)") 
					),
			inverseJoinColumns = @JoinColumn(
					name="id_producto", 
					nullable = false, 
					unique = true, 
					foreignKey = @javax.persistence.ForeignKey(foreignKeyDefinition = "foreign key (id_producto) references Productos(id_producto)") 
					)
	)
	private List<Cliente> clientes = new ArrayList<>();

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	
	
}
