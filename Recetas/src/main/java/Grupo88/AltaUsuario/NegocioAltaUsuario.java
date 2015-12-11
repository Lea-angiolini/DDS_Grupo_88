package Grupo88.AltaUsuario;

import java.io.Serializable;
import java.util.ArrayList;

import master.ErrorPage;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import Database.DAOComplexiones;
import Database.DAOCondicionesPreexistentes;
import Database.DAODietas;
import Database.DAOPreferenciasAlimenticias;
import Database.DAORutinas;
import Database.DAOSexo;
import Database.DAOUsuarios;
import ObjetosDB.Complexiones;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Dietas;
import ObjetosDB.PreferenciasAlimenticias;
import ObjetosDB.Rutinas;
import ObjetosDB.Sexo;
import ObjetosDB.Usuario;

public class NegocioAltaUsuario implements Serializable {

	private static final long serialVersionUID = 48472359735180319L;
	private DAOComplexiones daoComplexiones;
	private DAOCondicionesPreexistentes daoCondicionesPreexistentes;
	private DAODietas daoDietas;
	private DAORutinas daoRutinas;
	private DAOPreferenciasAlimenticias daoPreferenciasAlimenticias;
	private DAOSexo daoSexo;
	private DAOUsuarios daoUsuarios;
	ArrayList<Sexo> todosSexos;
	ArrayList<Complexiones> todasComplexiones;
	ArrayList<PreferenciasAlimenticias> todasPreferenciasAlim;
	ArrayList<CondicionesPreexistentes> todasCondiciones;
	ArrayList<Rutinas> todasRutinas;
	ArrayList<Dietas> todasDietas;

	public NegocioAltaUsuario(Session session) {
		
		daoComplexiones =  new DAOComplexiones(session);
		daoCondicionesPreexistentes = new DAOCondicionesPreexistentes(session);
		daoDietas = new DAODietas(session);
		daoRutinas = new DAORutinas(session);
		daoPreferenciasAlimenticias = new DAOPreferenciasAlimenticias(session);
		daoSexo = new DAOSexo(session);
		// no subio los .java
		daoUsuarios = new DAOUsuarios(session);
		
	}
	
	
	public boolean cargarListas(){
		try {
			todosSexos =  new ArrayList<Sexo>(daoSexo.findAll());
			todasComplexiones = new ArrayList<Complexiones>(daoComplexiones.findAll());
			todasPreferenciasAlim = new ArrayList<PreferenciasAlimenticias>(daoPreferenciasAlimenticias.findAll());
			todasCondiciones = new ArrayList<CondicionesPreexistentes>(daoCondicionesPreexistentes.findAll());
			todasRutinas = new ArrayList<Rutinas>(daoRutinas.findAll());
			todasDietas = new ArrayList<Dietas>(daoDietas.findAll());
			return true;
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
		
	}	
	
	public void guardarUsuario(Usuario usuario) throws ConstraintViolationException, javax.validation.ConstraintViolationException, Exception{

			daoUsuarios.saveOrUpdate(usuario);
	}
	
	
public ArrayList<Sexo> getTodosSexos() {
		return todosSexos;
	}


	public void setTodosSexos(ArrayList<Sexo> todosSexos) {
		this.todosSexos = todosSexos;
	}


	public ArrayList<Complexiones> getTodasComplexiones() {
		return todasComplexiones;
	}


	public void setTodasComplexiones(ArrayList<Complexiones> todasComplexiones) {
		this.todasComplexiones = todasComplexiones;
	}


	public ArrayList<PreferenciasAlimenticias> getTodasPreferenciasAlim() {
		return todasPreferenciasAlim;
	}


	public void setTodasPreferenciasAlim(
			ArrayList<PreferenciasAlimenticias> todasPreferenciasAlim) {
		this.todasPreferenciasAlim = todasPreferenciasAlim;
	}


	public ArrayList<CondicionesPreexistentes> getTodasCondiciones() {
		return todasCondiciones;
	}


	public void setTodasCondiciones(
			ArrayList<CondicionesPreexistentes> todasCondiciones) {
		this.todasCondiciones = todasCondiciones;
	}


	public ArrayList<Rutinas> getTodasRutinas() {
		return todasRutinas;
	}


	public void setTodasRutinas(ArrayList<Rutinas> todasRutinas) {
		this.todasRutinas = todasRutinas;
	}


	public ArrayList<Dietas> getTodasDietas() {
		return todasDietas;
	}


	public void setTodasDietas(ArrayList<Dietas> todasDietas) {
		this.todasDietas = todasDietas;
	}




}
