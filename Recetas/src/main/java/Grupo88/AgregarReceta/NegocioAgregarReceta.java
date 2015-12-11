package Grupo88.AgregarReceta;

import java.util.ArrayList;
import java.util.Set;

import org.hibernate.Session;

import master.ErrorPage;
import Database.DAOCondimentos;
import Database.DAODificultades;
import Database.DAOIngredientes;
import Database.DAORecetas;
import Database.DAOTemporadas;
import Database.DAOTipoReceta;
import Database.DBExeption;
import Grupo88.MisRecetas.MisRecetas;
import ObjetosDB.Condimentos;
import ObjetosDB.Dificultades;
import ObjetosDB.Ingredientes;
import ObjetosDB.Receta;
import ObjetosDB.Temporadas;
import ObjetosDB.TipoReceta;

public class NegocioAgregarReceta {
	
	private DAOCondimentos daoCondimentos;
	private DAODificultades daoDificultades;
	private DAOTemporadas daoTemporadas;
	private DAORecetas daoreceta;
	private DAOTipoReceta daoTipoReceta;
	private DAOIngredientes daoIngredientes;
	private ArrayList<Ingredientes> todosIngredientes;
	private ArrayList<Condimentos> todosCondimentos;
	private ArrayList<Temporadas> todasTemporadas;
	private ArrayList<Dificultades> todasDificultades;
	private ArrayList<TipoReceta> todosTipoReceta;
	
	public NegocioAgregarReceta(Session session) {
		daoIngredientes = new DAOIngredientes (session);
		daoCondimentos = new DAOCondimentos(session);
		daoDificultades = new DAODificultades(session);
		daoTemporadas = new DAOTemporadas(session);
		daoreceta = new DAORecetas (session);
		daoTipoReceta = new DAOTipoReceta(session);
	}
	
	public boolean cargarListas(){
		try {
			todosIngredientes = new ArrayList<Ingredientes>(daoIngredientes.findAll());
			todasTemporadas =  new ArrayList<Temporadas>(daoTemporadas.findAll());
			todasDificultades = new ArrayList<Dificultades>(daoDificultades.findAll());
			todosCondimentos= new ArrayList<Condimentos>(daoCondimentos.findAll());
			todosTipoReceta = new ArrayList<TipoReceta>(daoTipoReceta.findAll());
			return true;
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
	}
	
	public boolean guardarReceta(Receta receta){
		try {
			daoreceta.saveOrUpdate(receta);
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
	
	public boolean cargarIngyCond(Receta receta, Set<Ingredientes> listaIngredientes, Set<Condimentos> listaCondimentos){
		
		receta.agregarIngrediente(receta.getIngredientePrincipal(), 1);
			
			for(Ingredientes ing : listaIngredientes)
				if(ing.getId() != receta.getIngredientePrincipal().getId())
					receta.agregarIngrediente(ing,1);
		
		receta.setCondimentos(listaCondimentos);
		
		return true;
		
	}
	
	public ArrayList<Ingredientes> getTodosIngredientes() {
		return todosIngredientes;
	}

	public void setTodosIngredientes(ArrayList<Ingredientes> todosIngredientes) {
		this.todosIngredientes = todosIngredientes;
	}

	public ArrayList<Condimentos> getTodosCondimentos() {
		return todosCondimentos;
	}

	public void setTodosCondimentos(ArrayList<Condimentos> todosCondimentos) {
		this.todosCondimentos = todosCondimentos;
	}

	public ArrayList<Temporadas> getTodasTemporadas() {
		return todasTemporadas;
	}

	public void setTodasTemporadas(ArrayList<Temporadas> todasTemporadas) {
		this.todasTemporadas = todasTemporadas;
	}

	public ArrayList<Dificultades> getTodasDificultades() {
		return todasDificultades;
	}

	public void setTodasDificultades(ArrayList<Dificultades> todasDificultades) {
		this.todasDificultades = todasDificultades;
	}

	public ArrayList<TipoReceta> getTodosTipoReceta() {
		return todosTipoReceta;
	}

	public void setTodosTipoReceta(ArrayList<TipoReceta> todosTipoReceta) {
		this.todosTipoReceta = todosTipoReceta;
	}
	
	
}
