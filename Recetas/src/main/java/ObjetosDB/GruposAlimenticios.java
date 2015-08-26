package ObjetosDB;

public class GruposAlimenticios {
	
	int idGrupoAlim;
	String grupoAlim;
	public GruposAlimenticios(int idGrupoAlim, String grupoAlim) 
	{
		this.idGrupoAlim = idGrupoAlim;
		this.grupoAlim = grupoAlim;
	}
	
	public int getIdGrupoAlim() {
		return idGrupoAlim;
	}
	public void setIdGrupoAlim(int idGrupoAlim) {
		this.idGrupoAlim = idGrupoAlim;
	}
	public String getGrupoAlim() {
		return grupoAlim;
	}
	public void setGrupoAlim(String grupoAlim) {
		this.grupoAlim = grupoAlim;
	}

	
}
