package Database;

import org.hibernate.Session;

import ObjetosDB.PreferenciasAlimenticias;

public class DAOPreferenciasAlimenticias extends DAOGenerico<PreferenciasAlimenticias, Integer>{

	public DAOPreferenciasAlimenticias(Session session) {
		
		super(session);
	}
}
