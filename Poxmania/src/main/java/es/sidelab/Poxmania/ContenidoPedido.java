package es.sidelab.Poxmania;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contenido_pedidos")
public class ContenidoPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long idArticulo;
	private String nombreArticulo;
	private int cantidadArticulo;
	private long idPedido;
	
	

	protected ContenidoPedido() {
		// Used by SpringData
	}

	public ContenidoPedido(long idPedido,long idArticulo,int cantidadArticulo,String nombreArticulo) {
		this.idPedido = idPedido;
		this.idArticulo = idArticulo;		
		this.cantidadArticulo=cantidadArticulo;	
		this.nombreArticulo=nombreArticulo;
	}

		
	
	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(long idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getCantidadArticulo() {
		return cantidadArticulo;
	}

	public void setCantidadArticulo(int cantidadArticulo) {
		this.cantidadArticulo = cantidadArticulo;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	
	
}