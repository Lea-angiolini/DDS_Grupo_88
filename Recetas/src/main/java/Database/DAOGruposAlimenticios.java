package Database;

import org.hibernate.Session;

import ObjetosDB.GruposAlimenticios;

public class DAOGruposAlimenticios extends DAOGenerico<GruposAlimenticios, Integer>{
	
	public DAOGruposAlimenticios(Session session) {
		// TODO Auto-generated constructor stub
		super(session);
	}

}
