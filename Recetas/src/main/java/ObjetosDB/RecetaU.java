package ObjetosDB;

import java.io.Serializable;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.Browser;

public class RecetaU implements Serializable{
	
	private int idreceta;
	private String nombre;
	private String creador;
	private String detalle;
	private Dificultades dificultad;
	private Temporadas temporada;
	private Ingredientes ingredientePrincipal;
	private ArrayList<Ingredientes> ingredientes;
	private ArrayList<Condimentos> condimentos;
	private ArrayList<Pasos> pasos;
	private int calificacion;
	private String descripcion;


	public RecetaU(int id, String nom, String crea, Dificultades difi, Temporadas temp, Ingredientes ingPrin, String descripcion, int calif){
		setIdreceta(id);
		setNombre(nom);
		setCreador(crea);
		setDificultad(difi);
		setTemporada(temp);
		setIngredientePrincipal(ingPrin);
		ingredientes = new ArrayList<Ingredientes>();
		condimentos = new ArrayList<Condimentos>();
		pasos = new ArrayList<Pasos>();
		setCalificacion(calif);
		setDescripcion(descripcion);
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
	
	public ArrayList<Ingredientes> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(ArrayList<Ingredientes> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public ArrayList<Condimentos> getCondimentos() {
		return condimentos;
	}

	public void setCondimentos(ArrayList<Condimentos> condimentos) {
		this.condimentos = condimentos;
	}
	
	public void agregarIngrediente(Ingredientes ingrediente){
		ingredientes.add(ingrediente);
	}
	
	public void agregarCondimento(Condimentos condimento){
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
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void consulta(Usuario user){
		Browser.agregarHistConsultas(getIdreceta(),user.getUsername());
	}
	
	public boolean guardarReceta(){
		if(Browser.agregarReceta(this)){
			return Browser.agregarIngredientesyCondimentos(this);
		}
		return false;
	}
	
	public boolean calificar(Usuario user){
		return Browser.calUltimaConfirmacion(idreceta,user,calificacion);
	}
	
	public boolean aceptaCond(List<CondicionesPreexistentes> list){
		
		for(CondicionesPreexistentes cond : list){
			for(Ingredientes ing : getIngredientes()){
				if(!ing.accept(cond)){ JOptionPane.showMessageDialog(null, ing.getName());
					return false;}
			}
		}
		
		return true;
	}
}

