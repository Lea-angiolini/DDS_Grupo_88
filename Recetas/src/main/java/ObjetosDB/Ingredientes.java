package ObjetosDB;

public class Ingredientes {
	
	int idIngrediente;
	String ingrediente;
	int calorias;
	int idTipoIngrediente;
	
	

	public Ingredientes(int idIngrediente, String ingrediente, int calorias,int idTipoIngrediente)
	{
		this.idIngrediente = idIngrediente;
		this.ingrediente = ingrediente;
		this.calorias = calorias;
		this.idTipoIngrediente = idTipoIngrediente;
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

	public int getIdTipoIngrediente() {
		return idTipoIngrediente;
	}

	public void setIdTipoIngrediente(int idTipoIngrediente) {
		this.idTipoIngrediente = idTipoIngrediente;
	}
	
	
}
