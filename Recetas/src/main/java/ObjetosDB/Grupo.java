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
		return Browser.entrarGrupo(user.getUsername(), idGrupo);
	}
	
	public boolean sacarUsuario(Usuario user){
		return Browser.salirGrupo(user.getUsername(), idGrupo);
	}
}
