package es.sidelab.Poxmania;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContenidoPedidoRepository extends CrudRepository<ContenidoPedido, Long>  {
	
	List<ContenidoPedido> findByIdArticulo(long idArticulo);
	
	List<ContenidoPedido> findByIdPedido(long idPedido);	
	
	List<ContenidoPedido> findById(long id);
	
	

}
