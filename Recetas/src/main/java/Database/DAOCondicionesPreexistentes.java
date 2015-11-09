package Database;

import org.hibernate.Session;

import ObjetosDB.CondicionesPreexistentes;

public class DAOCondicionesPreexistentes extends DAOGenerico<CondicionesPreexistentes, Integer>{
	
	public DAOCondicionesPreexistentes(Session session) {
		super(session);
	}

}
