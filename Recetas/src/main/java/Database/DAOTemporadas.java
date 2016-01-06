package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.Temporadas;

public class DAOTemporadas extends DAOGenerico<Temporadas, Integer> implements Serializable{

	private static final long serialVersionUID = 454513381882232640L;

	public DAOTemporadas(Session session) {
		super(session);
	}

}
