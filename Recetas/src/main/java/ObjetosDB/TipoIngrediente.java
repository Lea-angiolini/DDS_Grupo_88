package ObjetosDB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
