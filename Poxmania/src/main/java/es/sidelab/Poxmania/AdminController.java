package es.sidelab.Poxmania;

//import java.awt.List;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController { //

	
	@Autowired
	private Usuario usuario; //sesion de usuario por defecto admin es falso y cesta vacia
	@Autowired
	private ProductoRepository repository;
	
	Iterable<Producto> productos;
	
	
	@RequestMapping("/modificararticulos") //modificar los articulos
	public ModelAndView modificar() {
				
		if(usuario.isAdmin()){
			productos = repository.findAll();
			return new ModelAndView("gestion_articulos").addObject("productos",productos);	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		return new ModelAndView("index").addObject("productos", productos);			
	}
	
	@RequestMapping("/dardealta") //dar de alta un nuevo articulo
	public ModelAndView dardealta() {
				
		if(usuario.isAdmin()){			
			return new ModelAndView("nuevo_articulo");	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		return new ModelAndView("index").addObject("productos", productos);			
	}
	
	@RequestMapping("/comprobaralta") //dar de alta un nuevo articulo
	public ModelAndView comprobacionarticulo(@RequestParam String	nomp, @RequestParam String	catp, @RequestParam String	prep, @RequestParam String	canp, @RequestParam String	desp) {
				
		if(usuario.isAdmin()){			
			
			if(!nomp.isEmpty()&&!prep.isEmpty()&&!canp.isEmpty()&&!desp.isEmpty()){//comprobar que solo meto en la BD si tiene todos los datos
				System.out.println("Nombre: "+nomp);
				System.out.println("Categoria: "+catp);
				System.out.println("Precio: "+Integer.parseInt(prep));
				System.out.println("Cantidad: "+Integer.parseInt(canp));
				System.out.println("Descripcion: "+desp);
				repository.save(new Producto(nomp,catp,"imagen",desp,Integer.parseInt(canp),Integer.parseInt(prep)));
			}
			
			return new ModelAndView("pantallaadministracion");	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		return new ModelAndView("index").addObject("productos", productos);			
	}
	
	@RequestMapping("/borrar") //dar de alta un nuevo articulo
	public ModelAndView borrarproducto(@RequestParam String	elemento) {
				
		if(usuario.isAdmin()){	
			repository.delete((long)Integer.parseInt(elemento));
			productos = repository.findAll();
			return new ModelAndView("gestion_articulos").addObject("productos",productos);	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		return new ModelAndView("index").addObject("productos", productos);			
	}
	


	

}