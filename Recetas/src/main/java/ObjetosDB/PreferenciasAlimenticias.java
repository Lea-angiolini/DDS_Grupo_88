package ObjetosDB;

public class PreferenciasAlimenticias {
	
	int idPreferencia;
	String preferencia;
	
	public PreferenciasAlimenticias(int id, String preferencia){
		setIdPreferencia(id);
		setPreferencia(preferencia);
	}
	public int getIdPreferencia() {
		return idPreferencia;
	}
	public void setIdPreferencia(int idPreferencia) {
		this.idPreferencia = idPreferencia;
	}
	public String getPreferencia() {
		return preferencia;
	}
	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}
}
