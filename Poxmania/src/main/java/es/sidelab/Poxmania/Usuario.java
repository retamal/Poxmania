package es.sidelab.Poxmania;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

public class Usuario {
	private Cesta miCesta;
	private boolean admin;
	
	public Usuario(){
		miCesta=new Cesta();
		admin=false;
	}

	public void setMiCesta(Cesta miCesta) {
		this.miCesta = miCesta;
	}

	public Cesta getMiCesta() {
		return miCesta;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
}