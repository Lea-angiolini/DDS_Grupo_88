package ObjetosDB;

import java.util.ArrayList;

public class Estadisticas {

	private ArrayList<String> topRecetasHombreSemana;
	private ArrayList<String> topRecetasHombreMes;
	private ArrayList<String> topRecetasMujer;
	private ArrayList<String> topDificultad;
	private ArrayList<String> topRecetas;
	
	public Estadisticas(){
		topRecetasHombreSemana = new ArrayList<String>();
		topRecetasHombreMes = new ArrayList<String>();
		topRecetasMujer  = new ArrayList<String>();
		topDificultad 	 = new ArrayList<String>();
		topRecetas 		 = new ArrayList<String>();
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
	
	public void agregarTopRecetasMujer(String receta){
		topRecetasMujer.add(receta);
	}
	
	public void agregarTopDificultad(String receta){
		topDificultad.add(receta);
	}
	
	public void agregarTopRecetas(String receta){
		topRecetas.add(receta);
	}
	
	public void agregarTopRecetasHombreSemana(String receta){
		topRecetasHombreSemana.add(receta);
	}
	
	public ArrayList<String> getTopRecetasHombreSemana() {
		return topRecetasHombreSemana;
	}

	public void setTopRecetasHombreSemana(ArrayList<String> topRecetasHombreSemana) {
		this.topRecetasHombreSemana = topRecetasHombreSemana;
	}


	public ArrayList<String> getTopRecetasHombreMes() {
		return topRecetasHombreMes;
	}


	public void setTopRecetasHombreMes(ArrayList<String> topRecetasHombreMes) {
		this.topRecetasHombreMes = topRecetasHombreMes;
	}
	
	public void agregarTopRecetasHombreMes(String receta){
		topRecetasHombreMes.add(receta);
	}
	
	
}
