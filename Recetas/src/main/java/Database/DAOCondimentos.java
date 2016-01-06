package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.Condimentos;

public class DAOCondimentos extends DAOGenerico<Condimentos,Integer> implements Serializable{

	private static final long serialVersionUID = -9037983474914905544L;

	public DAOCondimentos(Session session) {
		super(session);
	}

}
