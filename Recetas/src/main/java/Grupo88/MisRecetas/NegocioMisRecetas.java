package Grupo88.MisRecetas;

import java.io.Serializable;
import java.util.ArrayList;

import Database.DAORecetas;
import ObjetosDB.Receta;
import master.Negocio;
import objetosWicket.SesionUsuario;

public class NegocioMisRecetas extends Negocio implements Serializable{
	
	private DAORecetas daoRecetas;
	private SesionUsuario sesion;
	public NegocioMisRecetas(SesionUsuario sesion) {

		super(sesion);
		daoRecetas = new DAORecetas(sesion.getSessionDB());
		this.sesion = sesion;
	}
	
	public ArrayList<Receta> recetasParaUsuarioActual(){
		return new ArrayList<Receta>(sesion.getUsuarioActual().misRecetas());
	}
	
}
