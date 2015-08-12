package ObjetosDB;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Database.Browser;

public class RecetaU {
	
	private int idreceta;
	private String nombre;
	private String creador;
	private String detalle;
	private Dificultades dificultad;
	private Temporadas temporada;
	private Ingredientes ingredientePrincipal;
	private ArrayList<String> ingredientes;
	private ArrayList<String> condimentos;
	private ArrayList<Pasos> pasos;
	private int calificacion;


	public RecetaU(int id, String nom, String crea, Dificultades difi, Temporadas temp, Ingredientes ingPrin, int calif){
		setIdreceta(id);
		setNombre(nom);
		setCreador(crea);
		setDificultad(difi);
		setTemporada(temp);
		setIngredientePrincipal(ingPrin);
		ingredientes = new ArrayList<String>();
		condimentos = new ArrayList<String>();
		pasos = new ArrayList<Pasos>();
		setCalificacion(calif);
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
	
	
	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Dificultades getDificultad() {
		return dificultad;
	}
	public void setDificultad(Dificultades dificultad) {
		this.dificultad = dificultad;
	}
	
	public Temporadas getTemporada() {
		return temporada;
	}

	public void setTemporada(Temporadas temporada) {
		this.temporada = temporada;
	}
	
	public Ingredientes getIngredientePrincipal() {
		return ingredientePrincipal;
	}

	public void setIngredientePrincipal(Ingredientes ingredientePrincipal) {
		this.ingredientePrincipal = ingredientePrincipal;
	}
	
	public ArrayList<String> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(ArrayList<String> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public ArrayList<String> getCondimentos() {
		return condimentos;
	}

	public void setCondimentos(ArrayList<String> condimentos) {
		this.condimentos = condimentos;
	}
	
	public void agregarIngrediente(String ingrediente){
		ingredientes.add(ingrediente);
	}
	
	public void agregarCondimento(String condimento){
		condimentos.add(condimento);
	}
	
	public ArrayList<Pasos> getPasos() {
		return pasos;
	}

	public void setPasos(ArrayList<Pasos> pasos) {
		this.pasos = pasos;
	}
	
	public void agregarPaso(Pasos paso){
		this.pasos.add(paso);
	}
	
	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	public boolean guardarReceta(){
		return Browser.agregarReceta(this);
	}
}

