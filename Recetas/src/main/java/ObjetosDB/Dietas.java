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
@Table(name="Dietas")
public class Dietas implements Serializable{
	
	private static final long serialVersionUID = 7588181614905645673L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idDieta")
	private int idDieta;
	
	@NotNull(message="La dieta no puede estar vacía")
	@Size(min=1, max=30,message="La dieta debe contener entre 1 y 30 caracteres")
	@Column(name="tipoDieta")
	private String dieta;
	
	public Dietas() {
		// TODO Auto-generated constructor stub
	}
	
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
