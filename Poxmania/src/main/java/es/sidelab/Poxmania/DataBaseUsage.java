package es.sidelab.Poxmania;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*imports de web*/

@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private ProductoRepository repository;
	
	Iterable<Producto> productos;

	

	@Override
	public void run(String... args) throws Exception {
		// String nombre, String categoria, String imagen, String descripcion,
		// int cantidad,int precio

		// Meto productos de prueba
		// repository.save(new
		// Producto("PSVita","Videoconsolas","imagen","Videoconsola portatil de Sony. Color negro.",2,199));
		// repository.save(new
		// Producto("Intel i7 3770k","Informatica","imagen","Procesador a 4,0 GHz.",4,380));

		
		// Iterable<Producto> productos = repository.findAll();
	/*	productos = repository.findAll();
		for (Producto producto : productos) {
			System.out.println("Todos los productos: " + producto.getNombre());
		}
	*/	
	
	}

	@RequestMapping("/categorias")
	public ModelAndView categorias(@RequestParam String cat) {

		productos = repository.findByCategoria(cat); // en el boton pondra
														// cat=videojuegos etc
		return new ModelAndView("index").addObject("productos", productos);

	}

	@RequestMapping("/")
	public ModelAndView tablon() {
		productos = repository.findAll(); // cada vez que se recargue carga
											// todos los productos de la BD para
											// que siempre este actualizado
		return new ModelAndView("index").addObject("productos", productos);
	}

}
