package Database;

import org.hibernate.Session;

import ObjetosDB.Dietas;

public class DAODietas extends DAOGenerico<Dietas,Integer> {
	public DAODietas(Session session) {
	super(session);
	}

}
