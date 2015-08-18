package ObjetosDB;

import java.util.ArrayList;

public class Estadisticas {

	private ArrayList<String> topRecetasHombreSemana;
	private ArrayList<String> topRecetasHombreMes;
	private ArrayList<String> topRecetasMujerSemana;
	private ArrayList<String> topRecetasMujerMes;
	private ArrayList<String> topDificultad;
	private ArrayList<String> topRecetas;
	
	public Estadisticas(){
		topRecetasHombreSemana = new ArrayList<String>();
		topRecetasHombreMes = new ArrayList<String>();
		topRecetasMujerSemana  = new ArrayList<String>();
		topRecetasMujerMes  = new ArrayList<String>();
		topDificultad 	 = new ArrayList<String>();
		topRecetas 		 = new ArrayList<String>();
	}
	
	
	public ArrayList<String> getTopDificultad(){
		return topDificultad;
	}
	
	public ArrayList<String> getTopRecetas(){
		return topRecetas;
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


	public ArrayList<String> getTopRecetasMujerSemana() {
		return topRecetasMujerSemana;
	}


	public void setTopRecetasMujerSemana(ArrayList<String> topRecetasMujerSemana) {
		this.topRecetasMujerSemana = topRecetasMujerSemana;
	}


	public ArrayList<String> getTopRecetasMujerMes() {
		return topRecetasMujerMes;
	}


	public void setTopRecetasMujerMes(ArrayList<String> topRecetasMujerMes) {
		this.topRecetasMujerMes = topRecetasMujerMes;
	}
	
	public void agregarTopRecetasMujerMes(String receta){
		topRecetasMujerMes.add(receta);
	}
	
	public void agregarTopRecetasMujerSemana(String receta){
		topRecetasMujerSemana.add(receta);
	}
	
}
