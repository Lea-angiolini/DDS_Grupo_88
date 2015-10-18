package ObjetosDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Pasos")
public class Pasos implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idPaso")
	private int idPaso;
	
	@Column(name="numeroPaso")
	private int numPaso;
	
	@NotNull
	@Column(name="descripcion")
	@Size(min=1,max=2000)
	private String descripcionPaso;
	
	@Column(name="foto")
	private byte[] imagen;
	
	
	 @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name="idReceta")
 	 private Receta receta;
	 
	 public Pasos() {
		// TODO Auto-generated constructor stub
	}

	public Pasos(int num, String desc) {

		this.numPaso = num;
		this.descripcionPaso = desc;
		this.imagen = null;
	}

	public int getNumPaso() {
		return numPaso;
	}

	public void setNumPaso(int numPaso) {
		this.numPaso = numPaso;
	}

	public String getDescripcionPaso() {
		return descripcionPaso;
	}

	public void setDescripcionPaso(String descripionPaso) {
		this.descripcionPaso = descripionPaso;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
}
