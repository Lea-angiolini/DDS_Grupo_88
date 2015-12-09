package Database;

import java.util.List;

import org.hibernate.Session;

import ObjetosDB.Historial;

public abstract class DAOReportes {
	
	protected Session session;
	
	public DAOReportes(Session session) {
		this.session = session;
	}
	
	public abstract List<Historial> recetasmasconsultadas(String desde, String hasta) throws Exception;
		
}
