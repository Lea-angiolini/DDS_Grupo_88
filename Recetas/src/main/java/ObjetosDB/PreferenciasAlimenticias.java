package ObjetosDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PreferenciasAlimenticias")

public class PreferenciasAlimenticias implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idPreferencia")
	int idPreferencia;
	
	@Column(name="descripcion")
	String preferencia;
	
	public PreferenciasAlimenticias(int id, String preferencia){
		setIdPreferencia(id);
		setPreferencia(preferencia);
	}
	public int getIdPreferencia() {
		return idPreferencia;
	}
	public void setIdPreferencia(int idPreferencia) {
		this.idPreferencia = idPreferencia;
	}
	public String getPreferencia() {
		return preferencia;
	}
	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}
}
