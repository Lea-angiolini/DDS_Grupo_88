package ObjetosDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sexos")
public class Sexo implements Serializable{

	private static final long serialVersionUID = -1070071942432362168L;
	
	@Id
	@Column(name="idSexo")
	private int idSexo;
	
	@Column(name="descripcion")
	private String descripcion;
	
	public Sexo() {}
	
	public void setIdSexo(int idSexo) {
		this.idSexo = idSexo;
	}
	public int getIdSexo() {
		return idSexo;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
}
