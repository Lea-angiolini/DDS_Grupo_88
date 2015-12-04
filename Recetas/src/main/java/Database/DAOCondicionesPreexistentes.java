package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.CondicionesPreexistentes;

public class DAOCondicionesPreexistentes extends DAOGenerico<CondicionesPreexistentes, Integer> implements Serializable{
	
	private static final long serialVersionUID = 3980205580888557119L;

	public DAOCondicionesPreexistentes(Session session) {
		super(session);
	}

}
