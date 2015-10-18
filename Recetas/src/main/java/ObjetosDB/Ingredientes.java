package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@javax.persistence.Entity
@javax.persistence.Table(name = "ingredientes")
public class Ingredientes extends AlimDeReceta implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idIngrediente")
	private int idIngrediente;
	
	@Column(name="nombre")
	private String ingrediente;
	
	@Column(name="caloriasPorcion")
	private int calorias;
	
	@ManyToOne(/*fetch=FetchType.LAZY*/)
	@JoinColumn(name="tipoIngrediente")
	private TipoIngrediente idTipoIngrediente;
	
	@OneToMany(mappedBy="key.ingrediente")
	private Set<Receta_Ingrediente> relRecetas = new HashSet<Receta_Ingrediente>();
	
	public Ingredientes(){	}

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
		// TODO Auto-generated method stub
		return idIngrediente;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return ingrediente;
	}

	@Override
	public boolean accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visitarIngrediente(this);
	}
	
	
}
