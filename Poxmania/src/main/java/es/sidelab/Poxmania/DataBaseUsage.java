package es.sidelab.Poxmania;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		
		 // save a couple of customers
     /*   repository.save(new Producto("Jack", "Bauer"));
        repository.save(new Producto("Chloe", "O'Brian"));
        repository.save(new Producto("Kim", "Bauer"));
        repository.save(new Producto("David", "Palmer"));
        repository.save(new Producto("Michelle", "Dessler"));
*/
        // fetch all customers
        Iterable<Producto> productos = repository.findAll();
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
        System.out.println();

        // fetch an individual customer by ID
        Producto customer = repository.findOne(1L);
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        // fetch customers by last name
        List<Producto> bauers = repository.findByLastName("Bauer");
        System.out.println("Customer found with findByLastName('Bauer'):");
        System.out.println("--------------------------------------------");
        for (Producto bauer : bauers) {
            System.out.println(bauer);
        }
        
        repository.delete(bauers.get(0));
	}
}
