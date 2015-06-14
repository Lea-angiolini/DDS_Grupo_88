package ObjetosDB;

import java.io.Serializable;
import java.util.List;

import Database.Browser;

public class Usuario implements Serializable{

	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private char sexo;
	private int altura;
	private String complexion;
	private String rutina;
	private List<String> condiciones;
	private List<String> dietas;
	private List<Grupo> grupos;
	
	
	public Usuario(){
		
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


	public String getComplexion() {
		return complexion;
	}


	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}


	public String getRutina() {
		return rutina;
	}


	public void setRutina(String rutina) {
		this.rutina = rutina;
	}


	public List<String> getCondiciones() {
		return condiciones;
	}


	public void setCondiciones(List<String> condiciones) {
		this.condiciones = condiciones;
	}


	public List<String> getDietas() {
		return dietas;
	}


	public void setDietas(List<String> dietas) {
		this.dietas = dietas;
	}


	public List<Grupo> getGrupos() {
		return grupos;
	}


	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	
	
}
