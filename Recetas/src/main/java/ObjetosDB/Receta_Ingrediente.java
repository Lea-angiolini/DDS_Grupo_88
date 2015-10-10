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
		@NotNull
		@JoinColumn(name="idIngrediente")
		private Ingredientes ingrediente;
		@NotNull
	    @Column(name="idReceta")
	    private int aa; //Arreglar por Receta

	    protected Key () {
	      // for hibernate
	    }
	    
	    public Key (Ingredientes Ingredientes, int aa) {
	      this.ingrediente = Ingredientes;
	      this.aa = aa;
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

	public Receta_Ingrediente(/*RecetaU receta,*/ Ingredientes ingrediente, int cantidad) {
		//this.receta = receta;
		key = new Key(ingrediente,6);
		this.cantidad = cantidad;
	}
/*
	public RecetaU getReceta() {
		return receta;
	}

	public void setReceta(RecetaU receta) {
		this.receta = receta;
	}*/

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
