package ObjetosDB;

import java.util.ArrayList;

public class RecetaU {
	
	private int idreceta;
	private String nombre;
	private String creador;
	private String dificultad;
	private String temporada;
	private String ingredientePrincipal;
	private ArrayList<String> ingredientes;
	private ArrayList<String> condimentos;
	private ArrayList<String> pasos;

	public RecetaU(int id, String nom, String crea, String difi, String temp, String ingPrin){
		setIdreceta(id);
		setNombre(nom);
		setCreador(crea);
		setDificultad(difi);
		setTemporada(temp);
		setIngredientePrincipal(ingPrin);
		ingredientes = new ArrayList<String>();
		condimentos = new ArrayList<String>();
		pasos = new ArrayList<String>();
	}
	
	public int getIdreceta() {
		return idreceta;
	}
	public void setIdreceta(int idreceta) {
		this.idreceta = idreceta;
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
	
	
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	
	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		temporada = temporada;
	}
	
	public String getIngredientePrincipal() {
		return ingredientePrincipal;
	}

	public void setIngredientePrincipal(String ingredientePrincipal) {
		ingredientePrincipal = ingredientePrincipal;
	}
	
	public ArrayList<String> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(ArrayList<String> ingredientes) {
		ingredientes = ingredientes;
	}

	public ArrayList<String> getCondimentos() {
		return condimentos;
	}

	public void setCondimentos(ArrayList<String> condimentos) {
		condimentos = condimentos;
	}
	
	public void agregarIngrediente(String ingrediente){
		ingredientes.add(ingrediente);
	}
	
	public void agregarCondimento(String condimento){
		condimentos.add(condimento);
	}
	
	public ArrayList<String> getPasos() {
		return pasos;
	}

	public void setPasos(ArrayList<String> pasos) {
		this.pasos = pasos;
	}
	
	public void agregarPaso(String paso){
		pasos.add(paso);
	}
}

