package es.sidelab.Poxmania;

//import java.awt.List;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductoController {

	//private List<Anuncio> anuncios = new ArrayList<>();
	private List<Producto> productos = new CopyOnWriteArrayList<>(); //esta preparada para k añadan anuncios a la vez
	
	
		
	@RequestMapping("/")
	public ModelAndView inicio(HttpSession sesion) {
		if (sesion.isNew()){
			return new ModelAndView("bienvenida");
		}else{
			return new ModelAndView("index").addObject("productos",productos);	
		}
		
	}
	
	@RequestMapping("/anuncioanadido")
	public ModelAndView anadido(@RequestParam String nombre,@RequestParam String categoria,@RequestParam String imagen, @RequestParam String descripcion, @RequestParam int cantidad, @RequestParam int precio) {
		
		Producto product1 = new Producto(nombre, categoria, imagen, descripcion, cantidad, precio);
		productos.add(product1);
		return new ModelAndView("index").addObject("productos",productos);	
	}
	
	@RequestMapping("/poneranuncio")
	public ModelAndView anuncio(@RequestParam int var) {
		Producto producto = productos.get(var-1);
	
		return new ModelAndView("noticia_plantilla").addObject("nombre",producto.getNombre()).addObject("categoria",producto.getCategoria()).addObject("imagen",producto.getImagen()).addObject("descripcion",producto.getDescripcion()).addObject("cantidad",producto.getCantidad()).addObject("precio",producto.getPrecio());	
	}
	
	


}