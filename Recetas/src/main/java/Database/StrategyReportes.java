package Database;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ObjetosDB.Consulta;
import ObjetosDB.Usuario;

public abstract class StrategyReportes {
	
	public abstract ArrayList<Consulta> obtenerReporte(Usuario user, String inicio, String fin);
}
