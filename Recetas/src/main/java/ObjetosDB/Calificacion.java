package ObjetosDB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "calificaciones")
public class Calificacion implements Serializable{

	private static final long serialVersionUID = -72583039618066790L;
	
	@EmbeddedId
	private Key key;

	@NotNull(message="La calificación no puede estar vacía")
	@Min(value=1,message="La calificación mínima es 1")
	@Max(value=5, message="La calificación máxima es 5")
	@Column(name="calificacion")
	private int calificacion;
	
	public Calificacion() {	}
	
	
	
	public Calificacion(Usuario usercalificador, Receta receta, int calificacion) {
		this.key = new Key(usercalificador,receta);
		this.calificacion = calificacion;
	}



	public int getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;	
	}

	public Usuario getUserCalificador() {
		return key.userCalificador;
	}

	public void setUserCalificador(Usuario userCalificador) {
		key.userCalificador = userCalificador;
	}

	public Receta getReceta() {
		return key.receta;
	}

	public void setReceta(Receta receta) {
		key.receta = receta;
	}
	
	@Embeddable
	public class Key implements Serializable{

		private static final long serialVersionUID = 114222063731836066L;

		@NotNull
		@ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="userCalificador")
		public Usuario userCalificador;
		
		@NotNull
		@ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="idReceta")
		public Receta receta;

		public Key(Usuario userCalificador, Receta receta) {
			this.userCalificador = userCalificador;
			this.receta = receta;
		}
		
		public Key(){ }
		
	}
}