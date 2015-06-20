package ObjetosDB;

import java.util.ArrayList;
import java.util.List;

public class itemBuscador {
	
	// Array con los datos del menu desplegable
	List<String> ingredientesPrincipales = new ArrayList<String>();
	List<String> temporadas = new ArrayList<String>();
	List<String> dificultades = new ArrayList<String>();
	List<String> gruposAlimenticios = new ArrayList<String>();
	
	//setters por unidad
	//getters Array completo
	
	public List<String> getIngredientesPrincipales() {
		return ingredientesPrincipales;
	}
	public void setIngredientesPrincipal(String ingrediente) {
		ingredientesPrincipales.add(ingrediente);
	}
	public List<String> getTemporadas() {
		return temporadas;
	}
	public void setTemporada(String temporada) {
		temporadas.add(temporada);
	}
	public List<String> getDificultades() {
		return dificultades;
	}
	public void setDificultad(String dificultad) {
		dificultades.add(dificultad);
	}
	public List<String> getGruposAlimenticios() {
		return gruposAlimenticios;
	}
	public void setGrupoAlimenticio(String grupoAlimenticio) {
		gruposAlimenticios.add(grupoAlimenticio);
	}


}
