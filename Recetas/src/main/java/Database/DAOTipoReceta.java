package Database;

import org.hibernate.Session;

import ObjetosDB.TipoReceta;

public class DAOTipoReceta extends DAOGenerico<TipoReceta, Integer> {

	public DAOTipoReceta(Session session) {
		super(session);
	}
}