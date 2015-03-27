package es.sidelab.Poxmania;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContenidoPedidoRepository extends CrudRepository<ContenidoPedido, Long>  {
	
	List<Pedido> findByIdArticulo(long idArticulo);
	
	List<Pedido> findByIdPedido(long idPedido);	
	
	List<Pedido> findById(long id);
	
	

}
