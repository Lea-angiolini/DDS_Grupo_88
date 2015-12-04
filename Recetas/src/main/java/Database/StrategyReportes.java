package Database;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import ObjetosDB.Consulta;
import ObjetosDB.Usuario;

public abstract class StrategyReportes implements Serializable{
	
	private static final long serialVersionUID = -8967083156540600342L;

	public abstract ArrayList<Consulta> obtenerReporte(Usuario user, String inicio, String fin);
}
