package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.Dificultades;

public class DAODificultades extends DAOGenerico<Dificultades, Integer> implements Serializable{

	private static final long serialVersionUID = 7728271238623502877L;

	public DAODificultades(Session session) {
		super(session);
	}
}
