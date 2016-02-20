package Grupo88.MisRecetas;

import java.io.Serializable;
import java.util.ArrayList;

import master.Negocio;
import objetosWicket.SesionUsuario;
import ObjetosDB.Receta;

public class NegocioMisRecetas extends Negocio implements Serializable{
	

	private static final long serialVersionUID = 6759471156143234234L;
	private SesionUsuario sesion;
	public NegocioMisRecetas(SesionUsuario sesion) {

		super(sesion);
		this.sesion = sesion;
	}
	
	public ArrayList<Receta> recetasParaUsuarioActual(){
		return new ArrayList<Receta>(sesion.getUsuarioActual().misRecetas());
	}
	
}
