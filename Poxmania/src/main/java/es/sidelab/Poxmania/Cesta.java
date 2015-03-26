package es.sidelab.Poxmania;
/*NOTA: en application properties he añadido el puerto 6666 k es el k tengo en mi BBDD del sobremesa*/
import java.util.ArrayList; 

import org.springframework.beans.factory.annotation.Autowired;



public class Cesta {
	public ArrayList<Articulo> cestaCompra = new ArrayList<Articulo>();
	public float precio;
	public int num_productos;
	
	public ArrayList<Articulo> getCestaCompra() {
		return cestaCompra;
	}

	public void setCestaCompra(ArrayList<Articulo> cestaCompra) {
		this.cestaCompra = cestaCompra;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getNum_productos() {
		return num_productos;
	}

	public void setNum_productos(int num_productos) {
		this.num_productos = num_productos;
	}

	@Autowired
	private ProductoRepository repository;
	

	
	public Cesta(){
		
	}
	
	public void anadirProducto(Producto nuevoProducto){
		
	
		//Producto nuevoProducto = repository.findOne(nuevoProducto2.getId());//tener siempre actualizada la cantidad de productos con la bd para evitar problemas de compra simultanea ;)
		boolean limite=false;	
		boolean metido=false;
		for (Articulo articulo:cestaCompra){	//comprobar si esta metido ya		
			if ((articulo.getId()==nuevoProducto.getId())&&(articulo.getCantidad()<nuevoProducto.getCantidad())){ //si existe actualizo cantidad
				articulo.setCantidad(articulo.getCantidad()+1);
				metido=true;
			}else if ((articulo.getId()==nuevoProducto.getId())&&(articulo.getCantidad()>=nuevoProducto.getCantidad())){
				limite=true; //para evitar que meta articulos si ya esta en el tope de stock
			}
		}
		
		if ((!metido)&&(nuevoProducto.getCantidad()>=1)&&(!limite)){ //si no esta en la cesta y hay cantidad disponible
			cestaCompra.add(new Articulo(nuevoProducto.getId(),nuevoProducto.getNombre(),1,nuevoProducto.getPrecio()));
			metido=true;
		}
		
		if(metido){
			num_productos++;
			precio+=nuevoProducto.getPrecio();
		}
		
	}
	
	public void eliminarProducto(long idAbuscar,int cantidad){
		/*para tener una sola operacion, si la cantidad es -1 elimina el producto, en otro caso resta la cantidad*/
		
		Producto prodcompr= repository.findOne(idAbuscar);
		if(cantidad==-1){
			for (Articulo articulo:cestaCompra){			
				if (articulo.getId()==idAbuscar){ 
					precio-=articulo.getPrecio()*articulo.getCantidad();
					num_productos-=articulo.getCantidad();
					cestaCompra.remove(articulo);	
					
				}			
			}
		}else{
			for (Articulo articulo:cestaCompra){			
				if ((articulo.getId()==idAbuscar)&&(articulo.getCantidad()-cantidad<=prodcompr.getCantidad())){ 
					articulo.setCantidad(articulo.getCantidad()-cantidad);
					precio-=articulo.getPrecio()*cantidad;
					num_productos-=cantidad;					
				}else if((articulo.getId()==idAbuscar)&&(articulo.getCantidad()-cantidad>prodcompr.getCantidad())){
					//si mientras compra, alguien compra muchas unidades se actualiza con la mayor cantidad posible
					articulo.setCantidad(prodcompr.getCantidad());	
					precio-=articulo.getPrecio()*cantidad;
					num_productos-=cantidad;
				}				
			}
		}		
	}
}

