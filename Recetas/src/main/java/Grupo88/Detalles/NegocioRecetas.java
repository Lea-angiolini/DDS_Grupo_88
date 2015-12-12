package Grupo88.Detalles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import Database.DAOCalificacion;
import Database.DAOConfirmar;
import Database.DAOGrupos;
import Database.DAORecetas;
import Database.DAOTipoReceta;
import ObjetosDB.Calificacion;
import ObjetosDB.Confirmacion;
import ObjetosDB.Grupo;
import ObjetosDB.Receta;
import ObjetosDB.TipoReceta;
import ObjetosDB.Usuario;

public class NegocioRecetas implements Serializable{

	private static final long serialVersionUID = -2761007007476471175L;
	private Session session;
	private DAORecetas daoreceta;
	private DAOCalificacion daoCalificacion;
	private DAOConfirmar daoConfirmar;
	private DAOGrupos daoGrupos;
	private DAOTipoReceta daoTipoReceta;
	
	public NegocioRecetas(Session session) {
		
		this.session = session;
		this.daoreceta = new DAORecetas(session);
		this.daoCalificacion = new DAOCalificacion(session);
		this.daoConfirmar = new DAOConfirmar(session);
		this.daoGrupos = new DAOGrupos(session);
		this.daoTipoReceta = new DAOTipoReceta(session);
	}
	
	public Receta getReceta(int id) throws Exception{
		return daoreceta.get(id);
	}
	
	public ArrayList<TipoReceta> getTiposDeReceta() throws Exception{
		return (ArrayList<TipoReceta>) daoTipoReceta.findAll();
	}
	
	public boolean guardarCOnfirmacion(Confirmacion confirmacion){
		try {
			daoConfirmar.save(confirmacion);
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
	
	public List<Grupo> gruposde(Usuario user) throws Exception{
		return daoGrupos.gruposde(user);
	}
	
	public boolean compartirEnGrupo(ArrayList<Grupo> grupos, Receta receta){
		for (Grupo grupo : grupos) {
			grupo.agregarReceta(receta);
			try {
				daoGrupos.saveOrUpdate(grupo);
			} catch (ConstraintViolationException e) {
				e.printStackTrace();
				
			} catch (javax.validation.ConstraintViolationException e) {
				e.printStackTrace();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public Calificacion calificacionDe(Receta receta,Usuario user){
		Calificacion calificacion = null;
		try {
			calificacion = daoCalificacion.calificacionDe(receta, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(calificacion == null){
			calificacion = new Calificacion();
			calificacion.setReceta(receta);
			calificacion.setUserCalificador(user);
		}
		return calificacion;
	}
	
	public boolean userConfirmo(Receta receta, Usuario user){
		try {
			return daoConfirmar.userConfirmo(receta, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean guardarCalificacion(Calificacion calificacion){
		try {
			daoCalificacion.saveOrUpdate(calificacion);
			return true;
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		} catch (javax.validation.ConstraintViolationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
