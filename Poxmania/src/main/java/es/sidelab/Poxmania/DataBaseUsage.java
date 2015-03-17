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
	Iterable<Producto> productos;
	
	@Override
	public void run(String... args) throws Exception {
	//	String nombre, String categoria, String imagen, String descripcion, int cantidad,int precio
		
		//Meto productos de prueba
		//repository.save(new Producto("PSVita","Videoconsolas","imagen","Videoconsola portatil de Sony. Color negro.",2,199));
		//repository.save(new Producto("Intel i7 3770k","Informatica","imagen","Procesador a 4,0 GHz.",4,380));
		
		
		List<Producto>	todosLosProductos =	repository.findByCategoria("Videoconsolas");
		//todosLosProductos
		//Iterable<Producto> productos = repository.findAll();
		productos = repository.findAll();
		for (Producto producto : productos) {
            System.out.println("Todos los productos: "+producto.getNombre());
        }
		for (Producto producto : todosLosProductos) {
            System.out.println("Videoconsolas: "+producto.getNombre());
        }
		
		 
	//BORRAR COSAS DE LA BD
	/*	System.out.println("Ahora voy a borrar el primero de todas las videoconsolas que es PSVITA");
		repository.delete(todosLosProductos.get(0));
		
		for (Producto producto : todosLosProductos) {
            System.out.println("Videoconsolas: "+producto.getNombre());
        }
	*/	
		
	
		
		
		
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
		productos = repository.findAll(); //cada vez que se recargue carga todos los productos de la BD para que siempre este actualizado
		return new ModelAndView("index").addObject("productos",productos);
	}
	
}
