package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.PreferenciasAlimenticias;

public class DAOPreferenciasAlimenticias extends DAOGenerico<PreferenciasAlimenticias, Integer> implements Serializable{

	private static final long serialVersionUID = 8966312785439042734L;

	public DAOPreferenciasAlimenticias(Session session) {
		super(session);
	}
}
