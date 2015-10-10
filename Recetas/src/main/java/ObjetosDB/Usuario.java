package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import Database.Browser;

@Entity
@Table(name="Usuarios")
public class Usuario implements Serializable{

	@Id
	@NotNull
	@Size(min=1, max=30)
	@Column(name="nombreUsuario")
	private String username;
	
	@NotNull
	@Size(min=4, max=120)
	@Column(name="clave")
	private String password;
	
	@NotNull
	@Email
	@Size(min=1, max=60)
	@Column(name="mail")
	private String email;
	
	@NotNull
	@Size(min=1, max=30)
	@Column(name="nombre")
	private String nombre;
	
	@NotNull
	@Size(min=1, max=30)
	@Column(name="apellido")
	private String apellido;
	
	@Past
	@Column(name="fechaNac")
	private String fechaNacimiento;
	
	@NotNull
	@Column(name="sexo")
	private char sexo;
	
	@NotNull
	@Min(0)
	@Column(name="altura")
	private int altura;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Complexion")
	private Complexiones complexion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Rutinas")
	private Rutinas rutina;
	
	private PreferenciasAlimenticias preferencia;
	private List<CondicionesPreexistentes> condiciones;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Dietas")
	private Dietas dieta;
	
	private List<Grupo> grupos;
	
	
	public Usuario(){
		this.condiciones = new ArrayList<CondicionesPreexistentes>();
		
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

	public char getSexo() {
		return sexo;
	}


	public void setSexo(char sexo) {
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

	public PreferenciasAlimenticias getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(PreferenciasAlimenticias preferencia) {
		this.preferencia = preferencia;
	}

	public Rutinas getRutina() {
		return rutina;
	}


	public void setRutina(Rutinas rutina) {
		this.rutina = rutina;
	}


	public List<CondicionesPreexistentes> getCondiciones() {
		return condiciones;
	}


	public void setCondiciones(List<CondicionesPreexistentes> condiciones) {
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
	

	public List<Grupo> getGrupos() {
		return grupos;
	}


	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	public void agregarGrupo(Grupo grupo){
		grupos.add(grupo);
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
	
	public void cargarGrupos(){
		setGrupos(Browser.cargarGrupos(username));
	}
	
	public boolean estaEnGrupo(Grupo unGrupo){
		
		for(Grupo grupo : grupos){
			if (grupo.getIdGrupo() == unGrupo.getIdGrupo()){
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<RecetaU> cargarMisRecetas(){
		return Browser.cargarRecetasUsuario(username);
		
	}
	
	
	public String modificarPerfil(){
		return Browser.modificarPerfil(this);
	}
	
	public ArrayList<RecetaU> cargarHome(){
		return Browser.cargarHomeRecetas(this);
	}
	
	public ArrayList<RecetaU> filtrarRecetas(ArrayList<RecetaU> recetas){
		
		ArrayList<RecetaU> recetasFiltradas = new ArrayList<RecetaU>();
		
		for(RecetaU receta : recetas )
		{
			if(receta.aceptaCond(getCondiciones())){
				recetasFiltradas.add(receta);
			}
			
		}
 		
		return recetasFiltradas;
	}
}
