package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.Ingredientes;

public class DAOIngredientes extends DAOGenerico<Ingredientes, Integer> implements Serializable{
	
	private static final long serialVersionUID = -6520613459685691397L;

	public DAOIngredientes(Session session) {
		super(session);
	}
	
}
