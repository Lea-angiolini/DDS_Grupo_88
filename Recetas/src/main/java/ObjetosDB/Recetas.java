
package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Database.Browser;


public class Recetas implements Serializable{
	
	private ArrayList<Receta> coleccionRecetas;
	
	public Recetas() {
		coleccionRecetas = new ArrayList<Receta>();
	}
	
	public void agregarNuevaReceta(int id, String nom, String crea, Dificultades difi, Ingredientes ingPrinc){
		agregarReceta(new Receta(id,nom,crea,difi,ingPrinc));
	}
	
	public void agregarReceta(Receta rec){
		coleccionRecetas.add(rec);
	}

	public ArrayList<Receta> ObtenerColeccionRecetas(){
		return coleccionRecetas;
	}
	
	
	public class Receta implements Serializable{
		
		private int idreceta;
		private String nombre;
		private String creador;
		private Dificultades dificultad;
		private Ingredientes ingredientePrincipal;

		public Receta(int id, String nom, String crea, Dificultades difi, Ingredientes IngPrinc){
			setIdreceta(id);
			setNombre(nom);
			setCreador(crea);
			setDificultad(difi);
			setIngredientePrincipal(IngPrinc);
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
		
		
		public String getCreador() {
			return creador;
		}
		public void setCreador(String creador) {
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

		public void consulta(Usuario user){
			Browser.agregarHistConsultas(getIdreceta(),user.getUsername());
		}
		
	}	
}
