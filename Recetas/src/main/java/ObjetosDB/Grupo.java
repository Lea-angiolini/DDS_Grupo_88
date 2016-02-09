package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Grupos")
public class Grupo implements Serializable{
	
	private static final long serialVersionUID = -6889197720307310421L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idGrupo")
	private int idGrupo;
	
	@NotNull(message="Ingrese un nombre para su Grupo")
	@Size(min=3, max=30, message="El nombre debe tener entre 3 y 30 caracteres")
	@Column(name="nombreGrupo", unique=true)
	private String nombre;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="creador")
	private Usuario creador;
	
	@NotNull(message="El detalle no puede estar vacio")
	@Size(min=1, max=255, message="El detalle debe tener menos de 255 caracteres")
	@Column(name="detalle")
	private String detalle;
	
	@ManyToMany(cascade={CascadeType.ALL}, mappedBy="grupos", fetch=FetchType.LAZY) //cambiar a lazy
	private Set<Usuario> usuarios;
	
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinTable(name="relgruporeceta", joinColumns={@JoinColumn(name="idGrupo")}, inverseJoinColumns={@JoinColumn(name="idReceta")})
	private Set<Receta> recetas;
	
	public Grupo() {}
	
	public Grupo(int id, String nom, String det){
		setDetalle(det);
		setIdGrupo(id);
		setNombre(nom);
		usuarios = new HashSet<Usuario>();
		recetas = new HashSet<Receta>();
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
	public Usuario getCreador() {
		return creador;
	}
	public void setCreador(Usuario creador) {
		this.creador = creador;
		//creador.setMisGrupo(this);
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public Set<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(Set<Receta> recetas) {
		this.recetas = recetas;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public boolean agregarUsuario(Usuario user){
			setUsuario(user);
			user.agregarGrupo(this);
			return true;
	}
	
	public boolean sacarUsuario(Usuario user){
			getUsuarios().remove(user);
			user.eliminarGrupo(this);
			return true;
	}

	
	public boolean tieneReceta(int idReceta){
		
		for (Receta rec : getRecetas()){
			if (rec.getIdreceta() == idReceta){
				return true;
			}
		}
		return false;
	}
	
	public boolean agregarReceta(Receta receta){
		getRecetas().add(receta);
		return true;
		
	}
	
	public Set<Receta> obtenerRecetas(){
		return getRecetas();
	}
	
	public Set<Usuario> obtenerUsuarios(){
		return getUsuarios();
	}
}
