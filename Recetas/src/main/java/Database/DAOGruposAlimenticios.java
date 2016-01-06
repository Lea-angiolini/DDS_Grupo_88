package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.GruposAlimenticios;

public class DAOGruposAlimenticios extends DAOGenerico<GruposAlimenticios, Integer> implements Serializable{
	
	private static final long serialVersionUID = -2174852514425290439L;

	public DAOGruposAlimenticios(Session session) {
		super(session);
	}

}
