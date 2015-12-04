package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.Sexo;

public class DAOSexo extends DAOGenerico<Sexo, Integer> implements Serializable{

	private static final long serialVersionUID = 2737799040641096524L;

	public DAOSexo(Session session) {
		super(session);
	}
}
