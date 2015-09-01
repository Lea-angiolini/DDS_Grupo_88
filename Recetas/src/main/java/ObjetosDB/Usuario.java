package ObjetosDB;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Database.Browser;
import Grupo88.GestionarPerfil;
import ObjetosDB.Receta;

public class Usuario implements Serializable{

	private String username;
	private String password;
	private String email;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private char sexo;
	private int altura;
	private Complexiones complexion;
	private Rutinas rutina;
	private PreferenciasAlimenticias preferencia;
	private List<CondicionesPreexistentes> condiciones;
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
