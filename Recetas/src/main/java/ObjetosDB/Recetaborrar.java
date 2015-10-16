
package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.JOptionPane;

import Database.Browser;

	@Entity
	@Table(name = "recetas")
	@Deprecated
	public class Recetaborrar implements Serializable{
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "idReceta")
		private int idreceta;
		
		@Column(name = "nombre")
		private String nombre;
		
		@ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="creador")
		private Usuario creador;
		
		
		private Dificultades dificultad;
		private Ingredientes ingredientePrincipal;
		private String descripcion;

		public Recetaborrar(int id, String nom, String crea, Dificultades difi, Ingredientes IngPrinc, String desc){
			setIdreceta(id);
			setNombre(nom);
			//setCreador(crea);
			setDificultad(difi);
			setIngredientePrincipal(IngPrinc);
			setDescripcion(desc);
		}
		
		public int getIdreceta() {
			return idreceta;
		}
		public void setIdreceta(int idreceta) {
			this.idreceta = idreceta;
		}
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		
		public Usuario getCreador() {
			return creador;
		}
		public void setCreador(Usuario creador) {
			this.creador = creador;
		}
		
		
		public Dificultades getDificultad() {
			return dificultad;
		}
		public void setDificultad(Dificultades dificultad) {
			this.dificultad = dificultad;
		}
		
		public Ingredientes getIngredientePrincipal() {
			return ingredientePrincipal;
		}

		public void setIngredientePrincipal(Ingredientes ingredientePrincipal) {
			this.ingredientePrincipal = ingredientePrincipal;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public void consulta(Usuario user){
			Browser.agregarHistConsultas(getIdreceta(),user.getUsername());
		}
		
	}	
