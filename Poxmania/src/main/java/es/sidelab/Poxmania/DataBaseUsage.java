package es.sidelab.Poxmania;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



/*imports de web*/







@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private ProductoRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		
		repository.save(new Producto("adios","hola","hola","hola",1,3));
		 // save a couple of customers
     /*   repository.save(new Producto("Jack", "Bauer"));
        repository.save(new Producto("Chloe", "O'Brian"));
        repository.save(new Producto("Kim", "Bauer"));
        repository.save(new Producto("David", "Palmer"));
        repository.save(new Producto("Michelle", "Dessler"));
*/
        // fetch all customers
     /*   Iterable<Producto> productos = repository.findAll();
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
        
        */
	}
	
	@RequestMapping("/")
	public ModelAndView tablon() {

		return new ModelAndView("index");
	}
	
}
