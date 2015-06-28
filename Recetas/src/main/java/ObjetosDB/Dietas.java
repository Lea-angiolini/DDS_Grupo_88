package ObjetosDB;

public class Dietas {
	int idDieta;
	String dieta;
	
	public Dietas(int id, String dieta){
		setIdDietas(id);
		setDieta(dieta);
	}
	
	public int getIdDietas() {
		return idDieta;
	}
	public void setIdDietas(int idDietas) {
		this.idDieta = idDietas;
	}
	public String getDieta() {
		return dieta;
	}
	public void setDieta(String dieta) {
		this.dieta = dieta;
	}
	
}
