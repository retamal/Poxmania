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
public class CestaController { //

	
	@Autowired
	private Usuario usuario; //sesion de usuario por defecto admin es falso y cesta vacia
	@Autowired
	private ProductoRepository repository;
	
	Iterable<Producto> productos;
	
	
	

}