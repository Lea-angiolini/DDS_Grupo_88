package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.jetty.server.Authentication.User;

import Database.Browser;
import ObjetosDB.Recetaborrar;

@Entity
@Table(name="Grupos")
public class Grupo implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idGrupo")
	private int idGrupo;
	
	@Size(min=0, max=30)
	@Column(name="nombreGrupo", unique=true)
	private String nombre;
	
	@Size(min=0, max=30)
	@Column(name="creador")
	private String creador;
	
	@NotNull
	@Size(min=1, max=255)
	@Column(name="detalle")
	private String detalle;
	
	@ManyToMany(cascade={CascadeType.ALL}, mappedBy="grupos")
	private Set<Usuario> usuarios=new HashSet<Usuario>();
	
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
	
	public ArrayList<Receta> obtenerRecetas(){
		return Browser.obtenerRecetasGrupo(idGrupo);
	}
	
	public ArrayList<Usuario> obtenerUsuarios(){
		return Browser.obtenerUsuariosGrupo(this);
	}
}
