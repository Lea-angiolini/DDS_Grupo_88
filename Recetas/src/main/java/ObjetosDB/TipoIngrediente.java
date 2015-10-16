package ObjetosDB;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipoingrediente")
public class TipoIngrediente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idTipoIngrediente")
	private int idTipoIngrediente;
	
	@Column(name="descripcion")
	private String descripcion;

	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="relTipoIngGrupoAlim", joinColumns={@JoinColumn(name="idTipoIngrediente")}, inverseJoinColumns={@JoinColumn(name="idGrupoAlim")})
	private Set<GruposAlimenticios> gruposAlimenticios= new HashSet<GruposAlimenticios>();
	
	public TipoIngrediente() {}

	public int getIdTipoIngrediente() {
		return idTipoIngrediente;
	}

	public void setIdTipoIngrediente(int idTipoIngrediente) {
		this.idTipoIngrediente = idTipoIngrediente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
