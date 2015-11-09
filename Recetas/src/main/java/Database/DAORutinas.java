package Database;

import org.hibernate.Session;

import ObjetosDB.Rutinas;

public class DAORutinas extends DAOGenerico<Rutinas,Integer>{
	public DAORutinas(Session session) {
		super(session);
	}
}
