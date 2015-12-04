package Database;

import org.hibernate.Session;

import ObjetosDB.Sexo;

public class DAOSexo extends DAOGenerico<Sexo, Integer>{

	public DAOSexo(Session session) {
		super(session);
	}
}
