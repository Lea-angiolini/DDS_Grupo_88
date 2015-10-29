package Database;

import org.hibernate.Session;

import ObjetosDB.Ingredientes;

public class DAOIngredientes extends DAOGenerico<Ingredientes, Integer> {
	
	public DAOIngredientes(Session session) {
		// TODO Auto-generated constructor stub
		super(session);
	}
	
}
