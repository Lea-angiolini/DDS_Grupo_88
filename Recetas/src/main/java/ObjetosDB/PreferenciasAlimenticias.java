package ObjetosDB;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="PreferenciasAlimenticias")

public class PreferenciasAlimenticias implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idPreferencia")
	private int idPreferencia;
	
	@NotNull
	@Size(min=1, max=120)
	@Column(name="descripcion")
	private String preferencia;
	
	@ManyToMany(cascade={CascadeType.ALL}, mappedBy="preferencias")
	private Set<Usuario> usuarios= new HashSet<Usuario>();
	
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
