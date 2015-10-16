package ObjetosDB;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "relrecetaingredientes")
public class Receta_Ingrediente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	  public Key key;

	  @Embeddable
	  public static class Key implements Serializable{
		  
		@ManyToOne
		@JoinColumn(name="idIngrediente")
		private Ingredientes ingrediente;
		
		@ManyToOne
		@JoinColumn(name="idReceta")
	    private Receta receta; //Arreglar por Receta

	    protected Key () {
	      // for hibernate
	    }
	    
	    public Key (Ingredientes Ingredientes, Receta receta) {
	      this.ingrediente = Ingredientes;
	      this.receta = receta;
	    }
	  }
	//@ManyToOne()
	//@JoinColumn(name="idReceta")
	//private RecetaU receta;
	
	
	
	@Column(name="cantidad")
	private int cantidad;
	
	public Receta_Ingrediente() {
		// TODO Auto-generated constructor stub
	}

	public Receta_Ingrediente(Receta receta, Ingredientes ingrediente, int cantidad) {
		key = new Key(ingrediente,receta);
		this.cantidad = cantidad;
	}

	public Receta getReceta() {
		return key.receta;
	}

	public void setReceta(Receta receta) {
		this.key.receta = receta;
	}

	public Ingredientes getIngrediente() {
		return key.ingrediente;
	}

	public void setIngrediente(Ingredientes ingrediente) {
		key.ingrediente = ingrediente;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
