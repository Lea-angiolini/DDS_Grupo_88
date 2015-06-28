package ObjetosDB;

public class Temporadas {
	
	int idTemporada;
	String temporada;
	
	public Temporadas(int idTemporada, String temporada) {
		this.idTemporada = idTemporada;
		this.temporada = temporada;
	}

	public int getIdTemporada() {
		return idTemporada;
	}

	public void setIdTemporada(int idTemporada) {
		this.idTemporada = idTemporada;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	
}
