package ObjetosDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Temporadas")

public class Temporadas implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTemporada")
	int idTemporada;
	
	@Column(name="nombreTemporada")
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
