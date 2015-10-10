package ObjetosDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Dietas")
public class Dietas implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idDieta")
	private int idDieta;
	
	@Column(name="tipoDieta")
	private String dieta;
	
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
