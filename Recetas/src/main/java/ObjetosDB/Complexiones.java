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
@Table(name="Complexion")
public class Complexiones implements Serializable{
	
	private static final long serialVersionUID = 4807575995629496200L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idComplexion")
	private int idComplexion;
	
	@NotNull(message="La complexión no puede estar vacío")
	@Size(min=1, max=30, message="La complexión debe contener ente 1 y 30 caracteres")
	@Column(name="complexion")
	private String complexion;
	
	public Complexiones() {
		// TODO Auto-generated constructor stub
	}
	
	public Complexiones(int id, String complexion){
		setIdComplexion(id);
		setComplexion(complexion);
	}
	public int getIdComplexion() {
		return idComplexion;
	}
	public void setIdComplexion(int idComplexion) {
		this.idComplexion = idComplexion;
	}
	public String getComplexion() {
		return complexion;
	}
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}
}
