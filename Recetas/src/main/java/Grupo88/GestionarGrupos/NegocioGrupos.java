package Grupo88.GestionarGrupos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import Database.DAOGrupos;
import Database.DAOUsuarios;
import ObjetosDB.Grupo;
import ObjetosDB.Usuario;

public class NegocioGrupos implements Serializable {

	private static final long serialVersionUID = -1324481714495775974L;
	private DAOGrupos daogrupos;
	private DAOUsuarios daoUsuarios;
	private List<Grupo> todosGrupos;
	private Session session;
	private String error;
	
	public NegocioGrupos(Session session) {

		daogrupos = new DAOGrupos(session);
		daoUsuarios = new DAOUsuarios(session);
		this.session = session;
	}
	
	public boolean cargarListas(){
		try {
			todosGrupos = (List<Grupo>) daogrupos.findAll();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Grupo> getTodosGrupos() {
		return todosGrupos;
	}
	
	public List<Grupo> getGruposCon(String nom){
		if(nom == null)
			return todosGrupos;
		
		try {
			return daogrupos.gruposCon(nom);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Grupo>();
	}
	
	public void setTodosGrupos(List<Grupo> todosGrupos) {
		this.todosGrupos = todosGrupos;
	}
	public boolean sacarUsuario(Usuario user, Grupo grupo){
		user.getGrupos().remove(grupo);
		grupo.getUsuarios().remove(user);
		
		try {
			daoUsuarios.saveOrUpdate(user);
			return true;
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (javax.validation.ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean agregarUsuario(Usuario user, Grupo grupo){
		user.agregarGrupo(grupo);
		try{
			try {
				daoUsuarios.saveOrUpdate(user);
				return true;
			} catch (ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (javax.validation.ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.getGrupos().remove(grupo);
		return false;
	}
	
	public boolean nuevoGrupo(Grupo grupo, Usuario user){
		
		try{
			try {
				//grupo.getUsuarios().add(user);
				daogrupos.save(grupo);
				user.getGrupos().add(grupo);
				daoUsuarios.saveOrUpdate(user);
				return true;
			} catch (ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = e.getMessage();
			} catch (javax.validation.ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				error = e.getConstraintViolations().iterator().next().getMessage();
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error = e.getMessage();
		}
		user.getGrupos().remove(grupo);
		return false;
	}
	
	public Grupo grupoPorId(int id){
		try {
			return daogrupos.get(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean admiteUsuario(Grupo grupo, Usuario user){
		return user.getGrupos().contains(grupo);
	}
	
	public String getError(){
		return this.error;
	}
}
