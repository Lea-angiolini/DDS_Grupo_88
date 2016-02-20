package ObjetosDB;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = -5724134759517737833L;

	@Id
	@NotNull(message="El nombre de usuario no puede estar vacio")
	@Size(min=1, max=30, message="El nombre de usuario debe tener entre 1 y 30 caracteres")
	@Column(name="nombreUsuario")
	private String username;
	
	@NotNull(message="Ingrese una contraseña")
	@Size(min=4, max=120, message="La contraseña debe contener entre 4 y 120 caracteres")
	@Column(name="clave")
	private String password;
	
	@NotNull(message="Ingrese un Email")
	@Size(min=1, max=60)
	@Column(name="mail", unique= true)
	private String email;
	
	@NotNull(message="Ingrese su nombre")
	@Size(min=1, max=30, message="El nombre debe contener entre 1 y 30 caracteres")
	@Column(name="nombre")
	private String nombre;
	
	@NotNull(message="Ingrese su apellido")
	@Size(min=1, max=30, message="El apellido debe contener entre 1 y 30 caracteres")
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="fechaNac")
	@NotNull(message="Ingrese una fecha de Nacimiento")
	private String fechaNacimiento;
	
	@NotNull(message="Seleccione un sexo")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sexo")
	private Sexo sexo;
	
	@NotNull(message="Ingrese su altura")
	@Min(0)
	@Column(name="altura")
	private int altura;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idComplexion")
	private Complexiones complexion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idRutina")
	private Rutinas rutina;
	
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY) 
	@JoinTable(name="relUsuarioPreferencia", joinColumns={@JoinColumn(name="nombreUsuario")}, inverseJoinColumns={@JoinColumn(name="idPreferencia")})
	private Set<PreferenciasAlimenticias> preferencias=new HashSet<PreferenciasAlimenticias>();
	
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinTable(name="relUsuarioCondicion", joinColumns={@JoinColumn(name="nombreUsuario")}, inverseJoinColumns={@JoinColumn(name="idCondicion")})
	private Set<CondicionesPreexistentes> condiciones=new HashSet<CondicionesPreexistentes>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idDieta")
	private Dietas dieta;
	
	@ManyToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	@JoinTable(name="relUsuarioGrupo", joinColumns={@JoinColumn(name="nombreUsuario")}, inverseJoinColumns={@JoinColumn(name="idGrupo")})
	private Set<Grupo> grupos;
	
	
	@OneToMany(mappedBy="creador",cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Grupo> misGrupos = new HashSet<Grupo>();
	
	@OneToMany(mappedBy="creador", fetch=FetchType.LAZY) // cambiar a lazy
	private Set<Receta> misRecetas = new HashSet<Receta>();
	
	/*@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="amigos", joinColumns={@JoinColumn(name="nombreUsuario1")}, inverseJoinColumns={@JoinColumn(name="nombreUsuario2")})
	private Set<Usuario> amigos=new HashSet<Usuario>();
	
	@ManyToMany(cascade={CascadeType.ALL}, mappedBy="amigos")
	private Set<Usuario> usuarios=new HashSet<Usuario>();*/
	
	public Usuario() {
	}
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Sexo getSexo() {
		return sexo;
	}


	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}


	public int getAltura() {
		return altura;
	}


	public void setAltura(int altura) {
		this.altura = altura;
	}


	public Complexiones getComplexion() {
		return complexion;
	}


	public void setComplexion(Complexiones complexion) {
		this.complexion = complexion;
	}

	public Set<PreferenciasAlimenticias> getPreferencia() {
		return preferencias;
	}

	public void setPreferencia(Set<PreferenciasAlimenticias> preferencia) {
		this.preferencias = preferencia;
	}

	public Rutinas getRutina() {
		return rutina;
	}


	public void setRutina(Rutinas rutina) {
		this.rutina = rutina;
	}


	public Set<CondicionesPreexistentes> getCondiciones() {
		return condiciones;
	}


	public void setCondiciones(Set<CondicionesPreexistentes> condiciones) {
		this.condiciones = condiciones;
	}
	
	
	public void setCondicion(CondicionesPreexistentes condicion) {
		this.condiciones.add(condicion);
	}
	

	public String getEmail() {
		return email;
	}
	

	public void setEmail(String email) {
		this.email = email;
	}
	

	public Dietas getDieta() {
		return dieta;
	}

	
	public void setDieta(Dietas dieta) {
		this.dieta = dieta;
	}
	

	public Set<Grupo> getGrupos() {
		return grupos;
	}


	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	public void agregarGrupo(Grupo grupo){
		grupos.add(grupo);
	}
	
	public Set<Grupo> getMisGrupos() {
		return misGrupos;
	}

	public void setMisGrupos(Set<Grupo> misGrupos) {
		this.misGrupos = misGrupos;
	}
	
	public void setMisGrupo(Grupo grupo) {
		this.misGrupos.add(grupo);
		//grupo.setCreador(this);
	}
	
	
	public Grupo getGrupoPorID(int id){
		Grupo grupoObtenido = null;
		
		for (Grupo grupo : getGrupos() ){
			if (grupo.getIdGrupo() == id){
				grupoObtenido = grupo;
			}
		}
		return grupoObtenido;
	}
	public void eliminarGrupo(Grupo grupoSacar){
		for (Grupo grupo : grupos){
			if (grupo.getIdGrupo() == grupoSacar.getIdGrupo()){
				grupos.remove(grupo);
				return;
			}
		}
	}
	public boolean tineCondPreex(CondicionesPreexistentes cond){
		
		for(CondicionesPreexistentes condPreex : condiciones ){
			
			if(condPreex.getIdCondPreex() == cond.getIdCondPreex()){
				return true;
			}
		}

		return false;
		
	}
	
	public Set<Receta> misRecetas(){
		return misRecetas;
	}
	
	
	public String modificarPerfil(){
		return "";
	}

	public boolean puedeComer(Receta receta){
		for(CondicionesPreexistentes cond : getCondiciones()){
			if(!receta.accept(cond)){
				return false;
			}
		}
		return true;
	}
}
