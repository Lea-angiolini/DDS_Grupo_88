package ObjetosDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Temporadas")

public class Temporadas implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTemporada")
	private int idTemporada;
	
	@NotNull(message="La temporada no puede estar vacía")
	@Size(min=1, max=50, message="La temporada debe contener entre 1 y 50 caracteres")
	@Column(name="nombreTemporada")
	private String temporada;
	
	public Temporadas() {
		// TODO Auto-generated constructor stub
	}
	
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
