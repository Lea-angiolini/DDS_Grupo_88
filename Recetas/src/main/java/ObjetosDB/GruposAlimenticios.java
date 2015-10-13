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
@Table(name="GrupoAlim")
public class GruposAlimenticios implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idGrupoAlim")
	private int idGrupoAlim;
	
	@NotNull
	@Size(min=1, max=50)
	@Column(name="descripcion")
	private String grupoAlim;
	
	@ManyToMany(cascade= {CascadeType.ALL}, mappedBy="gruposAlimenticios")
	private Set<TipoIngrediente> tiposIngredientes= new HashSet<TipoIngrediente>();
	
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
