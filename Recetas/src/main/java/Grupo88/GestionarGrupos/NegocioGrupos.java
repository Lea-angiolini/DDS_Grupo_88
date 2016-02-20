package Grupo88.GestionarGrupos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import master.Negocio;
import objetosWicket.SesionUsuario;
import Database.DAOGrupos;
import Database.DAOUsuarios;
import ObjetosDB.Grupo;
import ObjetosDB.Usuario;

public class NegocioGrupos extends Negocio implements Serializable {

	private static final long serialVersionUID = -1324481714495775974L;
	private DAOGrupos daogrupos;
	private DAOUsuarios daoUsuarios;
	private ArrayList<Grupo> todosGrupos;
	
	public NegocioGrupos(SesionUsuario sesion) {

		super(sesion);
		daogrupos = new DAOGrupos(sesion.getSessionDB());
		daoUsuarios = new DAOUsuarios(sesion.getSessionDB());
		cargarListas();
	}
	
	private boolean cargarListas(){
		try {
			todosGrupos =  (ArrayList<Grupo>) daogrupos.findAll();
			return true;
		} catch (Exception e) {
			todosGrupos = new ArrayList<Grupo>();
			setError(manejador.tratarExcepcion(e));
			return false;
		}
	}

	public ArrayList<Grupo> getTodosGrupos() {
		return todosGrupos;
	}
	
	public List<Grupo> getGruposCon(String nom){
		if(nom == null)
			return todosGrupos;
		
		try {
			return daogrupos.gruposCon(nom);
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return new ArrayList<Grupo>();
		}
	}
	
	public void setTodosGrupos(ArrayList<Grupo> todosGrupos) {
		this.todosGrupos = todosGrupos;
	}
	
	public boolean sacarUsuario(Usuario user, Grupo grupo){
		user.getGrupos().remove(grupo);
		grupo.getUsuarios().remove(user);
		
		try {
			daoUsuarios.saveOrUpdate(user);
			return true;
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return false;
		}
	}
	
	public boolean agregarUsuario(Usuario user, Grupo grupo){
		user.agregarGrupo(grupo);
		
		try{
			daoUsuarios.saveOrUpdate(user);
			grupo.agregarUsuario(user);
			return true;
		}
		catch (Exception e) {
			user.getGrupos().remove(grupo);
			setError(manejador.tratarExcepcion(e));
			return false;
		}
	}
	
	public boolean nuevoGrupo(Grupo grupo, Usuario user){
		
		try{
			//grupo.getUsuarios().add(user);
			daogrupos.save(grupo);
			user.getGrupos().add(grupo);
			daoUsuarios.saveOrUpdate(user);
			return true;
		}
		catch (Exception e) {
			user.getGrupos().remove(grupo);
			setError(manejador.tratarExcepcion(e));
			return false;
		}
	}
	
	public Grupo grupoPorId(int id){
		try {
			return daogrupos.grupoID(id);
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return null;
		}
		
	}
	
	public boolean admiteUsuario(Grupo grupo, Usuario user){
		return user.getGrupos().contains(grupo);
	}
	
}
