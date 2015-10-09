package ObjetosDB;

import java.io.Serializable;

public class Receta_Ingrediente implements Serializable {
	private RecetaU receta;
	private Ingredientes ingrediente;
	private int cantidad;
	
	public Receta_Ingrediente() {
		// TODO Auto-generated constructor stub
	}

	public Receta_Ingrediente(RecetaU receta, Ingredientes ingrediente, int cantidad) {
		this.receta = receta;
		this.ingrediente = ingrediente;
		this.cantidad = cantidad;
	}

	public RecetaU getReceta() {
		return receta;
	}

	public void setReceta(RecetaU receta) {
		this.receta = receta;
	}

	public Ingredientes getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingredientes ingrediente) {
		this.ingrediente = ingrediente;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
