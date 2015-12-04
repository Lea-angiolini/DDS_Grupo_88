package ObjetosDB;

public class Estadistico {

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
