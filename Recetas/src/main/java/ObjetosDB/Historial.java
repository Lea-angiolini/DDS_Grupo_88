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
@Table(name="historicoConsultas")
public class Historial implements Serializable{
	
	private static final long serialVersionUID = -1556291704058412473L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idHistorico")
	private int idHistorial;
	
	@Column(name="fecha")
	private String fecha;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idReceta")
	private Receta receta;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="username")
	private Usuario usuario;	
	
	public Historial() {	}
	
	
	public Historial(String fecha, Receta receta, Usuario usuario) {
		this.fecha = fecha;
		this.receta = receta;
		this.usuario = usuario;
	}


	public int getIdHistial() {
		
		return idHistorial;
	}
	
	public void setIdHistial(int idHistial) {
		
		this.idHistorial = idHistial;
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
	
	public Usuario getUsuario() {
		
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		
		this.usuario = usuario;
	}
	
}
