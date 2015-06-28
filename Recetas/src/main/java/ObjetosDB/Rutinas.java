package ObjetosDB;

public class Rutinas {
	
	int idRutina;
	String rutina;
	
	public Rutinas(int idRutina, String rutina){
		setIdRutina(idRutina);
		setRutina(rutina);
	}

	public int getIdRutina() {
		return idRutina;
	}

	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}

	public String getRutina() {
		return rutina;
	}

	public void setRutina(String rutina) {
		this.rutina = rutina;
	}
}
