package ObjetosDB;

import java.io.Serializable;

public class Estadistico implements Serializable{

	private static final long serialVersionUID = 2538411953657168487L;
	private String Descripcion;
	private String valor;
	
	

	public Estadistico(String descripcion, String valor) {
		super();
		Descripcion = descripcion;
		this.valor = valor;
	}
	
	public Estadistico(String descripcion, int valor) {
		super();
		Descripcion = descripcion;
		this.valor = String.valueOf(valor);
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
