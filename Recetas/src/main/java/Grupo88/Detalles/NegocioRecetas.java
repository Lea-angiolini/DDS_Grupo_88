package Grupo88.Detalles;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import master.Negocio;
import objetosWicket.SesionUsuario;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;

import Database.DAOCalificacion;
import Database.DAOConfirmar;
import Database.DAOConsultas;
import Database.DAOGrupos;
import Database.DAORecetas;
import Database.DAOTipoReceta;
import ObjetosDB.Calificacion;
import ObjetosDB.Confirmacion;
import ObjetosDB.Consulta;
import ObjetosDB.Grupo;
import ObjetosDB.Historial;
import ObjetosDB.Receta;
import ObjetosDB.TipoReceta;
import ObjetosDB.Usuario;

public class NegocioRecetas extends Negocio implements Serializable{

	private static final long serialVersionUID = -2761007007476471175L;
	//private SesionUsuario session;
	private DAORecetas daoreceta;
	private DAOCalificacion daoCalificacion;
	private DAOConfirmar daoConfirmar;
	private DAOGrupos daoGrupos;
	private DAOTipoReceta daoTipoReceta;
	private DAOConsultas daoConsultas;
	
	public NegocioRecetas(SesionUsuario sesion) {
		
		super(sesion);
		//this.session = session;
		this.daoreceta = new DAORecetas(sesion.getSessionDB());
		this.daoCalificacion = new DAOCalificacion(sesion.getSessionDB());
		this.daoConfirmar = new DAOConfirmar(sesion.getSessionDB());
		this.daoGrupos = new DAOGrupos(sesion.getSessionDB());
		this.daoTipoReceta = new DAOTipoReceta(sesion.getSessionDB());
		this.daoConsultas = new DAOConsultas(sesion.getSessionDB());
	}
	
	public Receta getReceta(int id){
		try {
			return daoreceta.get(id);
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return null;
		}
	}
	
	public ArrayList<TipoReceta> getTiposDeReceta(){
		try {
			return (ArrayList<TipoReceta>) daoTipoReceta.findAll();
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return new ArrayList<TipoReceta>();
		}
	}
	
	public boolean guardarCOnfirmacion(Confirmacion confirmacion){
		try {
			daoConfirmar.save(confirmacion);
			return true;
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return false;
		}
	}
	
	public List<Grupo> gruposde(Usuario user){
		try {
			return daoGrupos.gruposde(user);
		} catch (Exception e) {
			return new ArrayList<Grupo>();
		}
	}
	
	public boolean compartirEnGrupo(ArrayList<Grupo> grupos, Receta receta){
		for (Grupo grupo : grupos) {
			grupo.agregarReceta(receta);
			try {
				daoGrupos.saveOrUpdate(grupo);
				return true;
			} catch (Exception e) {
				setError(manejador.tratarExcepcion(e));
				return false;
			}
		}
		return true;
	}
	
	public Calificacion calificacionDe(Receta receta,Usuario user){
		Calificacion calificacion = null;
		try {
			calificacion = daoCalificacion.calificacionDe(receta, user);
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
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
			setError(manejador.tratarExcepcion(e));
			return false;
		}
	}
	
	public boolean guardarCalificacion(Calificacion calificacion){
		try {
			daoCalificacion.saveOrUpdate(calificacion);
			return true;
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return false;
		}
	}
	
	public boolean guardarConsulta(Historial consulta, Usuario user){
		
		if(user.getUsername() == "Invitado")
			return false;
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		String fecha = formato.format(new Date());
		
		consulta.setFecha(fecha);
		
		try {
			daoConsultas.saveOrUpdate(consulta);
			return true;
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return false;
		}
	}
}
