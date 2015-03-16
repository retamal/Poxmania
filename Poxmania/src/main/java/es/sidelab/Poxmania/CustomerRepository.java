package es.sidelab.Poxmania;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Producto, Long> {

	List<Producto> findByLastName(String lastName);
	
	List<Producto> findByFirstName(String firstName);
	
}