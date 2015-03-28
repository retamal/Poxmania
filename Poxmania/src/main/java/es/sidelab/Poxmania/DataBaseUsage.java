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
	@Autowired
	private Usuario usuario; //sesion de usuario por defecto admin es falso y cesta vacia
	
	Iterable<Producto> productos;

	

	@Override
	public void run(String... args) throws Exception {
		
		/* QUITAR PARA AÑADIR PRODUCTOS POR DEFECTO EN LA BD
		repository.save(new Producto("PSVita","Videoconsolas","vita.jpg","Videoconsola portatil de Sony. Color negro.",2,199));
		repository.save(new Producto("Intel i7 3770k","Informatica","i7.jpg","Procesador a 4,0 GHz.",5,180));
		repository.save(new Producto("SuperPOX-PC","Informatica","poxpc.jpg","Un gran PC prefabricado con componentes de baja calidad ideal para venderselo a pobres incautos",10,680));
		repository.save(new Producto("Monitor LG SV-12","Televisiones","lg.jpg","Monitor LG SV-12 de 22\" y 5ms de respuesta. Ideal para jugar en él. Color negro azabache.",8,99));
		repository.save(new Producto("GameBoy Color","Videoconsolas","gameboy.jpg","Vuelve la clásica GameBoy Color. Color morado transparente. Disfruta de los juegos de siempre.",6,40));
		repository.save(new Producto("Nevera Fagor T-103","Electrodomesticos","fagor.jpg","Nevera Fagor T-103 No-Frost de 3 puertas. Conserva los alimentos ahorrando luz.",6,380));
		repository.save(new Producto("N-Gage QD","Videoconsolas","ngage.jpg","Nokia renace como el ave fénix con su nuevo teléfono-videoconsola. Pantalla a color capaz de mover los juegos más exigentes del mercado.",3,160));
		*/
	
	}
	
	@RequestMapping("/buscarprecio")
	public ModelAndView porprecio(@RequestParam String min,@RequestParam String max) {
		if(!min.isEmpty()&&!max.isEmpty()){
			productos = repository.findByPrecioBetween(Float.parseFloat(min), Float.parseFloat(max));										
			int productos_en_carro = usuario.getMiCesta().getNum_productos();
			return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);
		}else{
			productos = repository.findAll();										
			int productos_en_carro = usuario.getMiCesta().getNum_productos();
			return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);
		}
	}
	
	@RequestMapping("/buscarnombre")
	public ModelAndView pornombre(@RequestParam String textoabuscar) {
		if(!textoabuscar.isEmpty()){
			productos = repository.findByNombreContaining(textoabuscar);										
			int productos_en_carro = usuario.getMiCesta().getNum_productos();
			return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);
		}else{
			productos = repository.findAll();										
			int productos_en_carro = usuario.getMiCesta().getNum_productos();
			return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);
		}
	}
	
	

	@RequestMapping("/categorias")
	public ModelAndView categorias(@RequestParam String cat) {

		productos = repository.findByCategoria(cat); // en el boton pondra
														// cat=videojuegos etc
		int productos_en_carro = usuario.getMiCesta().getNum_productos();
		return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);

	}

	@RequestMapping("/")
	public ModelAndView tablon() {
		productos = repository.findAll(); // cada vez que se recargue carga
											// todos los productos de la BD para
											// que siempre este actualizado
		
		int productos_en_carro = usuario.getMiCesta().getNum_productos();
		return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);
	}

}
