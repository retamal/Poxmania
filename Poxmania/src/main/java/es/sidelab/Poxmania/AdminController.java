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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController { //

	
	@Autowired
	private Usuario usuario; //sesion de usuario por defecto admin es falso y cesta vacia
	@Autowired
	private ProductoRepository repository;
	private static final String FILES_FOLDER = "files";
	
	@Autowired
	private PedidoRepository repositoryPedidos;
	
	@Autowired
	private ContenidoPedidoRepository repositoryContenidoPedidos;
	
	Iterable<Producto> productos;
	
	
	@RequestMapping("/modificararticulos") //modificar los articulos
	public ModelAndView modificar() {
				
		if(usuario.isAdmin()){
			productos = repository.findAll();
			return new ModelAndView("gestion_articulos").addObject("productos",productos);	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		int productos_en_carro = usuario.getMiCesta().getNum_productos();
		return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);	
	}
	
	@RequestMapping("/dardealta") //dar de alta un nuevo articulo
	public ModelAndView dardealta() {
				
		if(usuario.isAdmin()){			
			return new ModelAndView("nuevo_articulo");	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		int productos_en_carro = usuario.getMiCesta().getNum_productos();
		return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);	
	}
	
	@RequestMapping(value="/comprobaralta" , method = RequestMethod.POST) //dar de alta un nuevo articulo
	public ModelAndView comprobacionarticulo(@RequestParam String	nomp, @RequestParam String	catp, @RequestParam String	prep, @RequestParam String	canp, @RequestParam String	desp,@RequestParam MultipartFile imagen) {
				
		if(usuario.isAdmin()){			
			//System.out.println("PROBANDO");
			if(!nomp.isEmpty()&&!prep.isEmpty()&&!canp.isEmpty()&&!desp.isEmpty()){//comprobar que solo meto en la BD si tiene todos los datos
				/*System.out.println("Nombre: "+nomp);
				System.out.println("Categoria: "+catp);
				System.out.println("Precio: "+Float.parseFloat(prep));
				System.out.println("Cantidad: "+Integer.parseInt(canp));
				System.out.println("Descripcion: "+desp);*/
				
				Producto nuevoarticulo = new Producto(nomp,catp,"imagen",desp,Integer.parseInt(canp),Float.parseFloat(prep));
				
				
				repository.save(nuevoarticulo);	//meto para obtener el id			
				
				/******* TEMA DE LA IMAGEN ****/
				String fileName = "Producto_"+nuevoarticulo.getId() + ".jpg";
				if (!imagen.isEmpty()) { //si la imagen es valida
					try {
						File filesFolder = new File(FILES_FOLDER);
						if (!filesFolder.exists()) {//creo directorio si no existe
							filesFolder.mkdirs();
						}

						File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
						imagen.transferTo(uploadedFile);	
						
						nuevoarticulo.setImagen(fileName);
					} catch (Exception e) {
						System.out.println("Ha petado al subirse la imagen");
						nuevoarticulo.setImagen("nodisponible.jpg");
					}
				} else {
					System.out.println("No se ha seleccionado ninguna imagen");//articulo sin imagen
					nuevoarticulo.setImagen("nodisponible.jpg");				}
				
				
				/*************/				
				repository.save(nuevoarticulo); //actualiza el articulo con la imagen correctamente :D
				
			}
			
			return new ModelAndView("pantallaadministracion");	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		int productos_en_carro = usuario.getMiCesta().getNum_productos();
		return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);		
	}
	
	@RequestMapping("/borrar") //borrar un articulo
	public ModelAndView borrarproducto(@RequestParam String	elemento) {
				
		if(usuario.isAdmin()){	
			repository.delete((long)Integer.parseInt(elemento));
			productos = repository.findAll();
			return new ModelAndView("gestion_articulos").addObject("productos",productos);	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		int productos_en_carro = usuario.getMiCesta().getNum_productos();
		return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);			
	}
	
	@RequestMapping("/editar") //editar un articulo
	public ModelAndView editarproducto(@RequestParam String	elemento) {
				
		if(usuario.isAdmin()){	
			Producto sacado = repository.findOne((long)Integer.parseInt(elemento));			
			return new ModelAndView("editar_articulo").addObject("producto",sacado);	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		int productos_en_carro = usuario.getMiCesta().getNum_productos();
		return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);		
	}
	
	@RequestMapping(value="/comprobareditar" , method = RequestMethod.POST) //editar un articulo
	public ModelAndView comprobacionedicionarticulo(@RequestParam String	numid,@RequestParam String	nomp, @RequestParam String	catp, @RequestParam String	prep, @RequestParam String	canp, @RequestParam String	desp,@RequestParam MultipartFile imagen) {
				
		if(usuario.isAdmin()){			
			//System.out.println("PROBANDO");
			if(!nomp.isEmpty()&&!prep.isEmpty()&&!canp.isEmpty()&&!desp.isEmpty()){//comprobar que solo meto en la BD si tiene todos los datos
			/*	System.out.println("Nombre: "+nomp);
				System.out.println("Categoria: "+catp);
				System.out.println("Precio: "+Float.parseFloat(prep));
				System.out.println("Cantidad: "+Integer.parseInt(canp));
				System.out.println("Descripcion: "+desp);
				System.out.println("ID: "+numid);
			*/
				
				Producto articuloparaeditar = repository.findOne((long)Integer.parseInt(numid));
				
				articuloparaeditar.setCantidad(Integer.parseInt(canp));
				articuloparaeditar.setPrecio(Float.parseFloat(prep));
				articuloparaeditar.setCategoria(catp);
				articuloparaeditar.setDescripcion(desp);
				articuloparaeditar.setNombre(nomp);
				
				String imagenaux= articuloparaeditar.getImagen();
										
				
				// TEMA DE LA IMAGEN 
				String fileName = "Producto_"+articuloparaeditar.getId() + ".jpg";
				if (!imagen.isEmpty()) { //si la imagen es valida
					try {
						File filesFolder = new File(FILES_FOLDER);
						if (!filesFolder.exists()) {//creo directorio si no existe
							filesFolder.mkdirs();
						}

						File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
						imagen.transferTo(uploadedFile);	
						
						articuloparaeditar.setImagen(fileName);
					} catch (Exception e) {
						System.out.println("Ha petado al subirse la imagen");
						articuloparaeditar.setImagen(imagenaux);//imagen anterior
					}
				} else {
					System.out.println("No se ha seleccionado ninguna imagen");//articulo sin imagen
					articuloparaeditar.setImagen(imagenaux);	//imagen anterior
					}
				
				
								
				repository.save(articuloparaeditar); //actualiza el articulo con la imagen correctamente :D
				
			}
			
			return new ModelAndView("pantallaadministracion");	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		int productos_en_carro = usuario.getMiCesta().getNum_productos();
		return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);		
	}

	/*comprobar pedidos*/
	
	@RequestMapping("/visualizar_pedidos") //consulta los pedidos
	public ModelAndView visualizar_pedidos() {
				
		if(usuario.isAdmin()){
			
			Iterable<Pedido> pedidos = repositoryPedidos.findAll();			
			return new ModelAndView("gestion_pedidos").addObject("pedidos",pedidos);	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		int productos_en_carro = usuario.getMiCesta().getNum_productos();
		return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);	
	}
	
	

	@RequestMapping("/preparar") //borrar un articulo
	public ModelAndView preparar_pedido(@RequestParam String	elemento) {
				
		if(usuario.isAdmin()){				
			Pedido pedido = repositoryPedidos.findOne((long)Integer.parseInt(elemento));
			if(!pedido.isPreparado()){
				pedido.setPreparado(true);
				repositoryPedidos.save(pedido);
			}			
			Iterable<Pedido> pedidos = repositoryPedidos.findAll();			
			
			return new ModelAndView("gestion_pedidos").addObject("pedidos",pedidos);	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		return new ModelAndView("index").addObject("productos", productos);			
	}
	@RequestMapping("/examinar") //borrar un articulo
	public ModelAndView examinar_pedido(@RequestParam String	elemento) {
				
		if(usuario.isAdmin()){				
			Iterable<ContenidoPedido> con_pedidos = repositoryContenidoPedidos.findByIdPedido((long)Integer.parseInt(elemento));
			
			return new ModelAndView("gestion_examinar_pedido").addObject("pedidos",con_pedidos);	
		}				
		
		productos = repository.findAll(); // cada vez que se recargue carga		
		int productos_en_carro = usuario.getMiCesta().getNum_productos();
		return new ModelAndView("index").addObject("productos", productos).addObject("obj_en_carro",productos_en_carro);		
	}
	

}