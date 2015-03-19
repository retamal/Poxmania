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
public class ProductoController { //

	
	@Autowired
	private Usuario usuario; //sesion de usuario por defecto admin es falso y cesta vacia
	@Autowired
	private ProductoRepository repository;
	
	Iterable<Producto> productos;
	
	
	@RequestMapping("/validacion") //ADMINISTRACION //al darle a loguearse vienen aqui
	//FALLO SEGURIDAD SI PONGO EL NOMBRE DEL TEMPLATE CARGA?
	public ModelAndView validacion(@RequestParam String	user,@RequestParam String pass) {
				
		if((user.equals("admin"))&&(Integer.parseInt(pass)==1234)){
			System.out.println("CONTRASEÑA CORRECTA");
			usuario.setAdmin(true);
			return new ModelAndView("bienvenida");	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		return new ModelAndView("index").addObject("productos", productos);			
	}

	@RequestMapping("/acceso")
	public ModelAndView acceso() {//si esta puesto de admin 
		if (usuario.isAdmin()){
			return new ModelAndView("bienvenida");
		}else{
			return new ModelAndView("acceso");
		}
	}
	

}