package Database;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Confirmacion;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class DAOConfirmar extends DAOGenerico<Confirmacion, Integer> implements Serializable{

	private static final long serialVersionUID = -4125216434616689370L;

	public DAOConfirmar(Session session) {
		super(session);
	}
	
	public boolean userConfirmo(Receta receta, Usuario user) throws Exception{

		session.beginTransaction();
			
		Query query = session.createQuery("from Confirmacion c where c.receta.idreceta = "+receta.getIdreceta()+
														" and c.user.username = '"+user.getUsername()+"'");
		@SuppressWarnings("unchecked")
		List<Confirmacion> conf = (List<Confirmacion>) query.list();
			
		session.getTransaction().commit();
			
		return !conf.isEmpty();
			
	}
}
