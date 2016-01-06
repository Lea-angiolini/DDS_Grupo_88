package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.Historial;

public class DAOConsultas extends DAOGenerico<Historial, Integer> implements Serializable{

	private static final long serialVersionUID = -8575470424138338176L;

	public DAOConsultas(Session session) {
		super(session);
	}
}
