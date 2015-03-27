package es.sidelab.Poxmania;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Long>  {
	
	List<Pedido> findByNombreCliente(String nombreCliente);
	
	List<Pedido> findByNombreClienteContaining(String nombreCliente);
	
	List<Pedido> findByApellidos(String apellidos);
	
	List<Pedido> findById(long id);
	
	

}
