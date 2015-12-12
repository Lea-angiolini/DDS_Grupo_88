package Grupo88.Reportes;

import java.io.Serializable;

import org.hibernate.Session;

import Database.DAOReportes;
import ObjetosDB.Usuario;

public class NegocioReportes implements Serializable {

	private static final long serialVersionUID = -309099855638170941L;
	private Session session;
	private DAOReportes daoReportes;
	
	public NegocioReportes(Session session, Usuario user) {
		this.session = session;
		daoReportes = new DAOReportes(session, user);
	}
	
	public DAOReportes getDaoReportes() {
		return daoReportes;
	}
	
}
