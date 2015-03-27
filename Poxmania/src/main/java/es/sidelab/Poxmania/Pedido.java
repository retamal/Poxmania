package es.sidelab.Poxmania;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombreCliente;
	private String apellidos;	
	private boolean preparado;
	
	

	protected Pedido() {
		// Used by SpringData
	}

	public Pedido(String nombre, String apellidos,boolean preparado) {
		this.nombreCliente = nombre;
		this.apellidos = apellidos;		
		this.preparado=preparado;			
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	
	public boolean isPreparado() {
		return preparado;
	}

	public void setPreparado(boolean preparado) {
		this.preparado = preparado;
	}
	
}