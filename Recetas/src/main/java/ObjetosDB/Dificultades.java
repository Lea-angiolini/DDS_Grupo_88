package ObjetosDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dificultad")

public class Dificultades implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="isDificultad")
	int idDificultad;
	
	@Column(name="descripcion")
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
