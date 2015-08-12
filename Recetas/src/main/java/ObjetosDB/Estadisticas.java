package ObjetosDB;

import java.util.ArrayList;

public class Estadisticas {

	private ArrayList<String> topRecetasHombre;
	private ArrayList<String> topRecetasMujer;
	private ArrayList<String> topDificultad;
	private ArrayList<String> topRecetas;
	
	public Estadisticas(){
		topRecetasHombre = new ArrayList<String>();
		topRecetasMujer  = new ArrayList<String>();
		topDificultad 	 = new ArrayList<String>();
		topRecetas 		 = new ArrayList<String>();
	}
	
	public ArrayList<String> getTopRecetasHombre(){
		return topRecetasHombre;
	}
	
	public ArrayList<String> getTopRecetasMujer(){
		return topRecetasMujer;
	}
	
	public ArrayList<String> getTopDificultad(){
		return topDificultad;
	}
	
	public ArrayList<String> getTopRecetas(){
		return topRecetas;
	}
	
	public void agregarTopRecetasHombre(String receta){
		topRecetasHombre.add(receta);
	}
	
	public void agregarTopRecetasMujer(String receta){
		topRecetasMujer.add(receta);
	}
	
	public void agregarTopDificultad(String receta){
		topDificultad.add(receta);
	}
	
	public void agregarTopRecetas(String receta){
		topRecetas.add(receta);
	}
}
