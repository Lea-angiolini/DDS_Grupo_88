package Database;

import org.hibernate.Session;

import ObjetosDB.Condimentos;

public class DAOCondimentos extends DAOGenerico<Condimentos,Integer> {

	public DAOCondimentos(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

}
