package Grupo88.BuscarReceta;

import java.io.Serializable;
import java.util.ArrayList;

import master.Negocio;
import objetosWicket.SesionUsuario;
import Database.DAODificultades;
import Database.DAOGruposAlimenticios;
import Database.DAOIngredientes;
import Database.DAORecetas;
import Database.DAOTemporadas;
import ObjetosDB.Dificultades;
import ObjetosDB.GruposAlimenticios;
import ObjetosDB.Ingredientes;
import ObjetosDB.Receta;
import ObjetosDB.Temporadas;
import ObjetosDB.Usuario;
import ObjetosDB.itemsABuscar;

public class NegocioBuscarReceta extends Negocio implements Serializable{

	private static final long serialVersionUID = 5235761378170319909L;
	
	private DAODificultades daodificultades;
	private DAOTemporadas daotemporadas;
	private DAOIngredientes daoingredientes;
	private DAOGruposAlimenticios daogruposalimenticios;
	private DAORecetas daorecetas;
	
	private ArrayList<Dificultades> dificultades;
	private ArrayList<Temporadas> temporadas;
	private ArrayList<Ingredientes> ingredientes;
	private ArrayList<GruposAlimenticios> grpAlim;
	private ArrayList<Integer> calificaciones;
	
public NegocioBuscarReceta(SesionUsuario sesion){
	super(sesion);
	daodificultades = new DAODificultades(sesion.getSessionDB());
	daotemporadas = new DAOTemporadas(sesion.getSessionDB());
	daoingredientes = new DAOIngredientes(sesion.getSessionDB());
	daogruposalimenticios = new DAOGruposAlimenticios(sesion.getSessionDB());
	daorecetas = new DAORecetas(sesion.getSessionDB());
}

public boolean cargarListas(){
	try {
		dificultades = (ArrayList<Dificultades>) daodificultades.findAll();
		temporadas = (ArrayList<Temporadas>) daotemporadas.findAll();
		ingredientes = (ArrayList<Ingredientes>) daoingredientes.findAll();
		grpAlim =  (ArrayList<GruposAlimenticios>) daogruposalimenticios.findAll();
		
		calificaciones = new ArrayList<Integer>();
		for(int i = 1; i<= 5;i++){
			calificaciones.add(i);
		}
		
		return true;
		
	} catch (Exception e) {
		setError(manejador.tratarExcepcion(e));
		return false;
	}
}

public ArrayList<Receta> buscarPorFiltros(Usuario usuario, itemsABuscar queBuscar ){
	
	ArrayList<Receta> aceptadas = new ArrayList<Receta>();
	
	try {
		for(Receta rec : daorecetas.buscarReceta(queBuscar)){
			if(usuario.puedeComer(rec))
				aceptadas.add(rec);
		}
	} catch (Exception e) {
		setError(manejador.tratarExcepcion(e));
	}
	return aceptadas;
	
}

public ArrayList<Dificultades> getDificultades() {
	return dificultades;
}

public void setDificultades(ArrayList<Dificultades> dificultades) {
	this.dificultades = dificultades;
}

public ArrayList<Temporadas> getTemporadas() {
	return temporadas;
}

public void setTemporadas(ArrayList<Temporadas> temporadas) {
	this.temporadas = temporadas;
}

public ArrayList<Ingredientes> getIngredientes() {
	return ingredientes;
}

public void setIngredientes(ArrayList<Ingredientes> ingredientes) {
	this.ingredientes = ingredientes;
}

public ArrayList<GruposAlimenticios> getGrpAlim() {
	return grpAlim;
}

public void setGrpAlim(ArrayList<GruposAlimenticios> grpAlim) {
	this.grpAlim = grpAlim;
}

public ArrayList<Integer> getCalificaciones() {
	return calificaciones;
}

public void setCalificaciones(ArrayList<Integer> calificaciones) {
	this.calificaciones = calificaciones;
}
	
}
