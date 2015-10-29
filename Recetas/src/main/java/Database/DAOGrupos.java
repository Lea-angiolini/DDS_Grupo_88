package Database;

import org.hibernate.Session;

import ObjetosDB.Grupo;

public class DAOGrupos extends DAOGenerico<Grupo,Integer>{

	public DAOGrupos(Session session) {
		// TODO Auto-generated constructor stub
		super(session);
	}
}
