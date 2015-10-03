package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;


@javax.persistence.Entity
@javax.persistence.Table(name = "ingredientes")
public class Ingredientes extends AlimDeReceta implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idIngrediente")
	int idIngrediente;
	
	@Column(name="nombre")
	String ingrediente;
	
	@Column(name="caloriasPorcion")
	int calorias;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoIngrediente")
	TipoIngrediente idTipoIngrediente;
	
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
