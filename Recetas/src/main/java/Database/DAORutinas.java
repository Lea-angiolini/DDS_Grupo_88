package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.Rutinas;

public class DAORutinas extends DAOGenerico<Rutinas,Integer> implements Serializable{

	private static final long serialVersionUID = 8285134643707427917L;

	public DAORutinas(Session session) {
		super(session);
	}
}
