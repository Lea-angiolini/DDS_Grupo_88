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
@Table(name="Rutinas")

public class Rutinas implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idRutina")
	private int idRutina;
	
	@NotNull(message="La rutina no puede estar vacía")
	@Size(min=1, max=45, message="La rutinna debe contener entre 1 y 45 caracteres")
	@Column(name="rutina")
	private String rutina;
	
	public Rutinas() {
		// TODO Auto-generated constructor stub
	}
	
	public Rutinas(int idRutina, String rutina){
		setIdRutina(idRutina);
		setRutina(rutina);
	}

	public int getIdRutina() {
		return idRutina;
	}

	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}

	public String getRutina() {
		return rutina;
	}

	public void setRutina(String rutina) {
		this.rutina = rutina;
	}
}
