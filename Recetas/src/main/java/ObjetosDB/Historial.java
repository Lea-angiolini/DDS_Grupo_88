package ObjetosDB;

import java.util.Date;

import javax.persistence.Entity;


public class Historial {
	
	
	private int idHistial;
	private Date fecha;
	private Receta receta;
	private Usuario usuario;
	private int cantVecesUsada;
	private int calificacionUsuario;
	
	
	public int getIdHistial() {
		
		return idHistial;
	}
	
	public void setIdHistial(int idHistial) {
		
		this.idHistial = idHistial;
	}
	
	public Date getFecha() {
		
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		
		this.fecha = fecha;
	}
	
	public Receta getReceta() {
		
		return receta;
	}
	
	public void setReceta(Receta receta) {
		
		this.receta = receta;
	}
	
	public Usuario getUsuario() {
		
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		
		this.usuario = usuario;
	}
	
	public int getCantVecesUsada() {
		
		return cantVecesUsada;
	}
	
	public void setCantVecesUsada(int cantVecesUsada) {
		
		this.cantVecesUsada = cantVecesUsada;
	}
	
	public int getCalificacionUsuario() {
		
		return calificacionUsuario;
	}
	
	public void setCalificacionUsuario(int calificacionUsuario) {
		
		this.calificacionUsuario = calificacionUsuario;
	}

	
}
