package Grupo88.AltaUsuario;

import java.io.Serializable;
import java.util.ArrayList;

import master.Negocio;
import objetosWicket.SesionUsuario;
import Database.DAOComplexiones;
import Database.DAOCondicionesPreexistentes;
import Database.DAODietas;
import Database.DAOPreferenciasAlimenticias;
import Database.DAORutinas;
import Database.DAOSexo;
import Database.DAOUsuarios;
import Database.ManejadorExepciones;
import ObjetosDB.Complexiones;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Dietas;
import ObjetosDB.PreferenciasAlimenticias;
import ObjetosDB.Rutinas;
import ObjetosDB.Sexo;
import ObjetosDB.Usuario;

public class NegocioAltaUsuario extends Negocio implements Serializable {

	private static final long serialVersionUID = 48472359735180319L;
	private DAOComplexiones daoComplexiones;
	private DAOCondicionesPreexistentes daoCondicionesPreexistentes;
	private DAODietas daoDietas;
	private DAORutinas daoRutinas;
	private DAOPreferenciasAlimenticias daoPreferenciasAlimenticias;
	private DAOSexo daoSexo;
	private DAOUsuarios daoUsuarios;
	private ArrayList<Sexo> todosSexos;
	private ArrayList<Complexiones> todasComplexiones;
	private ArrayList<PreferenciasAlimenticias> todasPreferenciasAlim;
	private ArrayList<CondicionesPreexistentes> todasCondiciones;
	private ArrayList<Rutinas> todasRutinas;
	private ArrayList<Dietas> todasDietas;
	
	public NegocioAltaUsuario(SesionUsuario sesion) {
		super(sesion);
		manejador = new ManejadorExepciones(sesion);
		daoComplexiones =  new DAOComplexiones(sesion.getSessionDB());
		daoCondicionesPreexistentes = new DAOCondicionesPreexistentes(sesion.getSessionDB());
		daoDietas = new DAODietas(sesion.getSessionDB());
		daoRutinas = new DAORutinas(sesion.getSessionDB());
		daoPreferenciasAlimenticias = new DAOPreferenciasAlimenticias(sesion.getSessionDB());
		daoSexo = new DAOSexo(sesion.getSessionDB());
		daoUsuarios = new DAOUsuarios(sesion.getSessionDB());
		
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
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return false;
		}
		
	}	
	
	public boolean guardarUsuario(Usuario usuario){
		try {
			daoUsuarios.save(usuario);
			return true;
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return false;
		}
	}
	
	public boolean actualizarUsuario(Usuario usuario){
		try {
			daoUsuarios.saveOrUpdate(usuario);
			return true;
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return false;
		}
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
