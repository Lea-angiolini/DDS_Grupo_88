package Database;

import java.io.Serializable;
import java.util.ArrayList;

import ObjetosDB.Consulta;

public abstract class StrategyEstadisticas implements Serializable{
	
	private static final long serialVersionUID = -3525031402264050546L;
	protected ArrayList<ArrayList<Consulta>> todasConsultas = new ArrayList<ArrayList<Consulta>>();
	public abstract ArrayList<ArrayList<Consulta>> ObtenerConsulta(int dias);
}
