package Database;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Confirmacion;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class DAOConfirmar extends DAOGenerico<Confirmacion, Integer> {

	public DAOConfirmar(Session session) {
		super(session);
	}
	
	public boolean userConfirmo(Receta receta, Usuario user) throws Exception{
		try{
			session.beginTransaction();
			
			Query query = session.createQuery("from Confirmacion c where c.receta.idreceta = "+receta.getIdreceta()+
														" and c.user.username = '"+user.getUsername()+"'");
			List<Confirmacion> conf = (List<Confirmacion>) query.list();
			
			session.getTransaction().commit();
			
			return !conf.isEmpty();
			
		}
		catch(Exception ex){
			if(session.getTransaction().isActive()){
				session.getTransaction().rollback();
			}
			throw ex;
		}
	}
}
