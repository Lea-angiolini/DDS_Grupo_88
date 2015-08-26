package ObjetosDB;

public class Dificultades {
	
	int idDificultad;
	String dificultad;
	public Dificultades(int idDificultad, String dificultad) 
	{
		this.idDificultad = idDificultad;
		this.dificultad = dificultad;
	}
	public int getIdDificultad() {
		return idDificultad;
	}
	public void setIdDificultad(int idDificultad) {
		this.idDificultad = idDificultad;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
}
