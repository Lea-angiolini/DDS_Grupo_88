package ObjetosDB;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@javax.persistence.Entity
@javax.persistence.Table(name = "ingredientes")
public class Ingredientes extends AlimDeReceta implements Serializable{
	
	private static final long serialVersionUID = 8600966783603252487L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idIngrediente")
	private int idIngrediente;
	
	@NotNull(message="El ingrediente debe tener un nombre")
	@Size(min=1, max=30, message="El nombre debe contener entre 1 y 30 caracteres")
	@Column(name="nombre")
	private String ingrediente;
	
	@NotNull(message="Ingrese las calorías del ingediente")
	@Min(0)
	@Column(name="caloriasPorcion")
	private int calorias;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoIngrediente")
	private TipoIngrediente idTipoIngrediente;
	
	@OneToMany(mappedBy="key.ingrediente", fetch=FetchType.LAZY)
	private Set<Receta_Ingrediente> relRecetas = new HashSet<Receta_Ingrediente>();
	
	public Ingredientes(){}

	public Ingredientes(int idIngrediente, String ingrediente, int calorias,int idTipoIngrediente)
	{
		this.idIngrediente = idIngrediente;
		this.ingrediente = ingrediente;
		this.calorias = calorias;
		//this.idTipoIngrediente = idTipoIngrediente;
	}

	public int getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public int getCalorias() {
		return calorias;
	}

	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}

	public TipoIngrediente getIdTipoIngrediente() {
		return idTipoIngrediente;
	}

	public void setIdTipoIngrediente(TipoIngrediente idTipoIngrediente) {
		this.idTipoIngrediente = idTipoIngrediente;
	}
	
	public Set<Receta_Ingrediente> getRelRecetas() {
		return relRecetas;
	}

	public void setRelRecetas(Set<Receta_Ingrediente> relRecetas) {
		this.relRecetas = relRecetas;
	}
	
	public void setRecetaRelacionada(Receta_Ingrediente relRecetas) {
		this.relRecetas.add(relRecetas);
	}

	
	@Override
	public int getId() {
		return idIngrediente;
	}

	@Override
	public String getName() {
		return ingrediente;
	}
	
}
