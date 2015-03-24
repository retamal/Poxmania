package es.sidelab.Poxmania;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="articulos")
public class Articulo {

	//@Id	
	private long id;

	private String nombre;		
	private int cantidad;
	private float precio;
	

	protected Articulo() {
		// Used by SpringData
	}
	
	public Articulo(long id,String nombre,int cantidad,float precio){
		this.id=id;
		this.nombre = nombre;
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

}
