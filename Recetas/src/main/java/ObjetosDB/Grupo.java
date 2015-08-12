package ObjetosDB;

import org.eclipse.jetty.server.Authentication.User;

import Database.Browser;

public class Grupo {
	
	private int idGrupo;
	private String nombre;
	private String creador;
	private String detalle;
	
	public Grupo(int id, String nom, String cread, String det){
		setCreador(cread);
		setDetalle(det);
		setIdGrupo(id);
		setNombre(nom);
	}
	
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCreador() {
		return creador;
	}
	public void setCreador(String creador) {
		this.creador = creador;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public boolean agregarUsuario(Usuario user){
		if( Browser.entrarGrupo(user.getUsername(), idGrupo)){
			user.agregarGrupo(this);
			return true;
		}
		return false;
	}
	
	public boolean sacarUsuario(Usuario user){
		if(Browser.salirGrupo(user.getUsername(), idGrupo)){
			user.eliminarGrupo(this);
			return true;
		}
		return false;
	}
	
	public int agregarGrupo() {
		int dev = Browser.agregarNuevoGrupo(this);
		if(dev > 0){
			this.setIdGrupo(dev);
			Browser.entrarGrupo(getCreador(), getIdGrupo());
		}
		return dev;
		
	}
	
	public boolean tieneReceta(int idReceta){
		return Browser.grupoTieneReceta(getIdGrupo(),idReceta);
	}
	
	public boolean agregarReceta(int idReceta){
		return Browser.agregarRecetaGrupo(getIdGrupo(),idReceta);
		
	}
}
