package Database;

import java.util.ArrayList;

import ObjetosDB.Consulta;

public abstract class StrategyEstadisticas {
	
	protected ArrayList<ArrayList<Consulta>> todasConsultas = new ArrayList<ArrayList<Consulta>>();
	public abstract ArrayList<ArrayList<Consulta>> ObtenerConsulta(int dias);
}
