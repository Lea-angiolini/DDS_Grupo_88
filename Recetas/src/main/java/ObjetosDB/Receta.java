package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "recetas")
public class Receta implements Serializable, Visitable{
	
	private static final long serialVersionUID = -394756840419765018L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idReceta")
	private int idreceta;
	
	@NotNull(message="Ingrese un nombre para la receta")
	@Size(min=1, max=45, message="El nombre de la receta debe contener entre 1 y 45 caracteres")
	@Column(name = "nombre")
	private String nombre;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="creador")
	private Usuario creador;
	
	@Size(min=1, max=200, message="La descripción debe contener entre 1 y 200 caracteres")
	@Column(name = "descripcion")
	private String detalle;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idDificultad")
	private Dificultades dificultad;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="temporada")
	private Temporadas temporada;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ingredientePrincipal")
	private Ingredientes ingredientePrincipal;
	
	@OneToMany(mappedBy = "key.receta", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Receta_Ingrediente> ingredientesRelacionados;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="relrecetcondimento", joinColumns={@JoinColumn(name="idReceta")}, inverseJoinColumns={@JoinColumn(name="idCondimento")})
	private Set<Condimentos> condimentos;
	
	@OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="idReceta")
	@IndexColumn(name="numeroPaso")
	private List<Pasos> pasos;
	
	@OneToMany(mappedBy="key.receta",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Calificacion> calificaciones;
	
	@Column(name = "foto")
	private byte[] fotoPrincipal;
	
	@Column(name = "caloriasTotales")
	private int caloriasTotales;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idTipoReceta")
	private TipoReceta tipoReceta;

	public Receta(){
		ingredientesRelacionados = new HashSet<Receta_Ingrediente>();
		condimentos = new HashSet<Condimentos>();
		pasos = new ArrayList<Pasos>();
	}
	
	public Receta(int id, String nom, Usuario crea, Dificultades difi, Temporadas temp, Ingredientes ingPrin){
		setIdreceta(id);
		setNombre(nom);
		setCreador(crea);
		setDificultad(difi);
		setTemporada(temp);
		setIngredientePrincipal(ingPrin);
		ingredientesRelacionados = new HashSet<Receta_Ingrediente>();
		condimentos = new HashSet<Condimentos>();
		pasos = new ArrayList<Pasos>();
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
	
	
	public Usuario getCreador() {
		return creador;
	}
	public void setCreador(Usuario creador) {
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
	
	public Set<Receta_Ingrediente> getRelacionIngredientes() {
		return ingredientesRelacionados;
	}

	public void setRelacionIngredientes(Set<Receta_Ingrediente> ingredientesRelacionados) {
		this.ingredientesRelacionados = ingredientesRelacionados;
		calcularCalorias();
	}

	public Set<Condimentos> getCondimentos() {
		return condimentos;
	}

	public void setCondimentos(Set<Condimentos> condimentos) {
		this.condimentos = condimentos;
	}
	
	public void agregarIngrediente(Ingredientes ingrediente, int cantidad){
		Receta_Ingrediente relacion = new Receta_Ingrediente(this,ingrediente,cantidad);
		ingredientesRelacionados.add(relacion);
		caloriasTotales += ingrediente.getCalorias();
		//ingrediente.setRecetaRelacionada(relacion);
	}
	
	public void agregarCondimento(Condimentos condimento){
		condimentos.add(condimento);
	}
	
	public List<Pasos> getPasos() {
		return pasos;
	}

	public void setPasos(List<Pasos> pasos) {
		this.pasos = pasos;
	}
	
	public void agregarPaso(Pasos paso){
		this.pasos.add(paso);
	}
	
	public int getCalorias() {
		calcularCalorias();
		return caloriasTotales;
	}
	
	public byte[] getFotoPrincipal() {
		return fotoPrincipal;
	}

	public void setFotoPrincipal(byte[] fotoPrincipal) {
		this.fotoPrincipal = fotoPrincipal;
	}
	
	public boolean accept(Visitor visitor){
		return visitor.visitarReceta(this);
	}
	
	public void calcularCalorias(){
		caloriasTotales = 0;
		for(Receta_Ingrediente rel : ingredientesRelacionados){
			caloriasTotales += rel.getIngrediente().getCalorias();
		}
	}
}

