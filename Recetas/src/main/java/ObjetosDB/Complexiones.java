package ObjetosDB;

public class Complexiones {
	
	int idComplexion;
	String complexion;
	
	public Complexiones(int id, String complexion){
		setIdComplexion(id);
		setComplexion(complexion);
	}
	public int getIdComplexion() {
		return idComplexion;
	}
	public void setIdComplexion(int idComplexion) {
		this.idComplexion = idComplexion;
	}
	public String getComplexion() {
		return complexion;
	}
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}
}
