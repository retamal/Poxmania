package es.sidelab.Poxmania;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long>  {
	
	List<Producto> findByNombre(String nombre);
	
	List<Producto> findByCategoria(String categoria);
	
	List<Producto> findByPrecioBetween(int preciomenor,int preciomayor);

}
