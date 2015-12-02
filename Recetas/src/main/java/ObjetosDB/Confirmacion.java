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

@Entity
@Table(name="confirmadas")
public class Confirmacion implements Serializable {

	private static final long serialVersionUID = -8824509101351214206L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idConfirmada")
	private int IdConfirmacion;
	
	@Column(name="fecha")
	private String fecha;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idReceta")
	private Receta receta;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="usuario")
	private Usuario user;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoRecetaConf")
	private TipoReceta tipoReceta;

	public int getIdConfirmacion() {
		return IdConfirmacion;
	}

	public void setIdConfirmacion(int idConfirmacion) {
		IdConfirmacion = idConfirmacion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public TipoReceta getTipoReceta() {
		return tipoReceta;
	}

	public void setTipoReceta(TipoReceta tipoReceta) {
		this.tipoReceta = tipoReceta;
	}
	
	
}
