package ObjetosDB;

public class Calificacion {

	private String calificacion;
	private int valor;
	
	
	public Calificacion(String calificacion, int valor) {
		this.calificacion = calificacion;
		this.valor = valor;
	}
	public String getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	
}
