package master;

import java.io.Serializable;

import Database.ManejadorExepciones;
import objetosWicket.SesionUsuario;

public class Negocio implements Serializable {


	private static final long serialVersionUID = 440022998708860427L;
	private SesionUsuario sesion;
	private String error;
	protected ManejadorExepciones manejador;
	
	public Negocio(SesionUsuario sesion) {
		
		this.sesion = sesion;
		this.manejador = new ManejadorExepciones(sesion);
	}
	
	public SesionUsuario getSesion() {
		return sesion;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
}
