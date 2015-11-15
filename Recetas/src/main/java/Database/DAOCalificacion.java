package Database;

import org.hibernate.Session;

import ObjetosDB.Calificacion;

public class DAOCalificacion extends DAOGenerico<Calificacion, Integer> {

	public DAOCalificacion(Session session) {
		super(session);
	}
}
