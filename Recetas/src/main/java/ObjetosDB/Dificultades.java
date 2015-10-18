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
@Table(name="dificultad")

public class Dificultades implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idDificultad")
	private int idDificultad;
	
	@NotNull
	@Size(min=1, max=30)
	@Column(name="descripcion")
	private String dificultad;
	
	public Dificultades() {
		// TODO Auto-generated constructor stub
	}
	
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
