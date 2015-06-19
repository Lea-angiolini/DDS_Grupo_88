package ObjetosDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class Recetas implements Serializable{
	
	private ArrayList<Receta> coleccionRecetas;
	
	public Recetas() {
		coleccionRecetas = new ArrayList<Receta>();
	}
	
	public void agregarNuevaReceta(String nom, String crea, String difi){
		agregarReceta(new Receta(nom,crea,difi));
	}
	
	public void agregarReceta(Receta rec){
		coleccionRecetas.add(rec);
	}

	public ArrayList<Receta> ObtenerColeccionRecetas(){
		return coleccionRecetas;
	}
	
	
	public class Receta implements Serializable{
		
		private String nombre;
		private String creador;
		private String dificultad;

		public Receta(String nom, String crea, String difi){
			setNombre(nom);
			setCreador(crea);
			setDificultad(difi);
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
		
		
		public String getDificultad() {
			return dificultad;
		}
		public void setDificultad(String dificultad) {
			this.dificultad = dificultad;
		}
		
	}
	
	
	
}




