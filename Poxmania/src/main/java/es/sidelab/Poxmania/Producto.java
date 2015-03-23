package es.sidelab.Poxmania;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	private String categoria;
	private String imagen;
	private String descripcion;
	private int cantidad;
	private float precio;
	

	protected Producto() {
		// Used by SpringData
	}

	public Producto(String nombre, String categoria, String imagen, String descripcion, int cantidad,float precio) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.imagen=imagen;
		this.descripcion=descripcion;
		this.cantidad=cantidad;
		this.precio=precio;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
/*	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}*/
}