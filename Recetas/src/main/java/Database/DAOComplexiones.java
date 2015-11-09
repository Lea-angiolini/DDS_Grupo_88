package Database;

import org.hibernate.Session;

import ObjetosDB.Complexiones;

public class DAOComplexiones extends DAOGenerico<Complexiones, Integer>{
	
	public DAOComplexiones(Session sesion) {
		super(sesion);
	}
}
