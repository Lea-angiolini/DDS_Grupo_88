package ObjetosDB;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="GrupoAlim")
public class GruposAlimenticios implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idGrupoAlim")
	private int idGrupoAlim;
	
	@NotNull(message="El grupo alimenticio no puede estar vacío")
	@Size(min=1, max=50, message="El grupo alimenticio debe contener entre 1 y 50 caracteres")
	@Column(name="descripcion")
	private String grupoAlim;
	
	@OneToMany(mappedBy="grupoQuePertenece", cascade= CascadeType.ALL, fetch=FetchType.EAGER) //cambiar a lazy
	private Set<TipoIngrediente> tiposIngredientes= new HashSet<TipoIngrediente>();
	
	public GruposAlimenticios() {
		// TODO Auto-generated constructor stub
	}
	
	public GruposAlimenticios(int idGrupoAlim, String grupoAlim) 
	{
		this.idGrupoAlim = idGrupoAlim;
		this.grupoAlim = grupoAlim;
	}
	
	public int getIdGrupoAlim() {
		return idGrupoAlim;
	}
	public void setIdGrupoAlim(int idGrupoAlim) {
		this.idGrupoAlim = idGrupoAlim;
	}
	public String getGrupoAlim() {
		return grupoAlim;
	}
	public void setGrupoAlim(String grupoAlim) {
		this.grupoAlim = grupoAlim;
	}

	
}
