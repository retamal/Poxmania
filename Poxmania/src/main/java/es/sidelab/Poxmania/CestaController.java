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
	
	@Autowired
	private PedidoRepository repositoryPedidos;
	
	@Autowired
	private ContenidoPedidoRepository repositoryContenidoPedidos;
	
	Iterable<Producto> productos;
	
	@RequestMapping("/anadir_producto_cesta") //borrar un articulo
	public ModelAndView anadir_producto_cesta(@RequestParam String id) {
				
		
		
		Producto prodaux = repository.findOne(Long.parseLong(id));
		
		usuario.getMiCesta().anadirProducto(prodaux);;
		
		
		
		return new ModelAndView("visualizar_cesta").addObject("cesta", usuario.getMiCesta());			
	}
	
	@RequestMapping("/eliminar_producto_cesta") //borrar un articulo
	public ModelAndView eliminar_producto_cesta(@RequestParam String id) {
				
		
		
		Producto prodaux = repository.findOne(Long.parseLong(id));
		
		usuario.getMiCesta().eliminarProducto(prodaux);
		
		
		
		return new ModelAndView("visualizar_cesta").addObject("cesta", usuario.getMiCesta());			
	}
	
	@RequestMapping("/disminuir_producto_cesta") //borrar un articulo
	public ModelAndView disminuir_producto_cesta(@RequestParam String id) {
				
		
		
		Producto prodaux = repository.findOne(Long.parseLong(id));
		
		usuario.getMiCesta().disminuirProducto(prodaux);		
		
		return new ModelAndView("visualizar_cesta").addObject("cesta", usuario.getMiCesta());			
	}
	
	@RequestMapping("/micesta") //borrar un articulo
	public ModelAndView micesta() {
				
		
		return new ModelAndView("visualizar_cesta").addObject("cesta", usuario.getMiCesta());			
	}
	
	@RequestMapping("/hacer_pedido") //borrar un articulo
	public ModelAndView hacer_pedido(@RequestParam String nomc,@RequestParam String apec) {
				
		Pedido nuevoPedido = new Pedido(nomc,apec,false); //preparado false
		repositoryPedidos.save(nuevoPedido);//genero una id de pedido
		
		
		for (Articulo articulo:usuario.getMiCesta().getCestaCompra()){
			repositoryContenidoPedidos.save(new ContenidoPedido(nuevoPedido.getId(),articulo.getId(),articulo.getCantidad()));//id pedido id articulo cantidad
		}		
		
		usuario.vaciarCesta(); //borra la cesta del usuario		
		
		
		return new ModelAndView("visualizar_cesta").addObject("cesta", usuario.getMiCesta());			
	}
	
	@RequestMapping("/formulario_pedido") //borrar un articulo
	public ModelAndView formulario_pedido() {				
		/*comprobar que exista el stock*/
		boolean cambios_en_cesta=false;
		for (Articulo articulo:usuario.getMiCesta().getCestaCompra()){
			Producto prodaux=repository.findOne(articulo.getId());
			if(prodaux.getCantidad()<articulo.getCantidad()){ //si han habido cambios
				int diferencia = articulo.getCantidad()-prodaux.getCantidad(); //tengo que quitar a la cantidad diferencia
				usuario.getMiCesta().precio-=articulo.getPrecio()*diferencia;
				usuario.getMiCesta().num_productos-=diferencia;
				articulo.setCantidad(articulo.getCantidad()-diferencia);
				cambios_en_cesta=true;
			}
		}
		/*fin comprobacion*/
		
		if(usuario.getMiCesta().getNum_productos()==0){
			productos = repository.findAll(); // cada vez que se recargue carga		
			return new ModelAndView("index").addObject("productos", productos);			
		}else{
			return new ModelAndView("formulario_pedido").addObject("cesta", usuario.getMiCesta()).addObject("cambios",cambios_en_cesta);		
		}
		
		
			
	}
	
	

}