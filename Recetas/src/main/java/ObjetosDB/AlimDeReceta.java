package ObjetosDB;

import java.util.ArrayList;

public abstract class AlimDeReceta implements Visitor {
	private ArrayList<Integer> condAptas;
	public abstract int getId();
	public abstract String getName();
	
	public ArrayList<Integer> getCondAptas() {
		return condAptas;
	}
	public void setCondAptas(ArrayList<Integer> condAptas) {
		this.condAptas = condAptas;
	}
	
	public void setCondApta(Integer condAptas) {
		this.condAptas.add(condAptas);
	}
	
	public boolean esApta(int idCondPreex){
		
		for(int condApta : condAptas){
			if(condApta == idCondPreex)
				return true;
		}
		
		return false;
	}
}
