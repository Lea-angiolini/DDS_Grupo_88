package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.Dietas;

public class DAODietas extends DAOGenerico<Dietas,Integer> implements Serializable{
	
	private static final long serialVersionUID = -8447676510206658151L;

	public DAODietas(Session session) {
	super(session);
	}

}
