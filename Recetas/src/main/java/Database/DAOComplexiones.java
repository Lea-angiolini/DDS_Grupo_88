package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.Complexiones;

public class DAOComplexiones extends DAOGenerico<Complexiones, Integer> implements Serializable{
	
	private static final long serialVersionUID = 7824757631106991265L;

	public DAOComplexiones(Session sesion) {
		super(sesion);
	}
}
