package Database;

import org.hibernate.Session;

import ObjetosDB.Calificacion;

public class DAOCalificacion extends DAOGenerico<Calificacion, Calificacion.Key> {

	public DAOCalificacion(Session session) {
		super(session);
	}
}
