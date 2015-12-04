package Database;

import java.io.Serializable;

import org.hibernate.Session;

import ObjetosDB.TipoReceta;

public class DAOTipoReceta extends DAOGenerico<TipoReceta, Integer> implements Serializable{

	private static final long serialVersionUID = 8760321511965381530L;

	public DAOTipoReceta(Session session) {
		super(session);
	}
}