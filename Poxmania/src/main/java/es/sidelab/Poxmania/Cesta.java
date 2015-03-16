package es.sidelab.Poxmania;

import java.util.ArrayList;

public class Cesta {
	public ArrayList<Producto> cestaCompra = new ArrayList<Producto>();
	
	public Cesta(){
		
	}
	
	public void anadirProducto(Producto nuevoProducto){
		
		boolean metido=false;
		for (Producto producto:cestaCompra){			
			if (producto.getNombre().equalsIgnoreCase(nuevoProducto.getNombre())){
				producto.setCantidad(producto.getCantidad()+1);
				metido=true;
			}
		}
		
		if (!metido){
			cestaCompra.add(nuevoProducto);
		}
		
	}
	
	public void eliminarProducto(Producto viejoProducto){
		cestaCompra.remove(viejoProducto);
	}
	
	public Producto obtenerProducto(int posicion){
		return cestaCompra.get(posicion);
	}

}
