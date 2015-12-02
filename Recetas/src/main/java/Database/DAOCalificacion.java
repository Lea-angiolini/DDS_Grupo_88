package Database;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Calificacion;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class DAOCalificacion extends DAOGenerico<Calificacion, Calificacion.Key> {

	public DAOCalificacion(Session session) {
		super(session);
	}
	
	public Calificacion calificacionDe(Receta receta, Usuario user) throws Exception{
		
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Calificacion where key.userCalificador = '"+user.getUsername()
											+"' and key.receta.idreceta = '"+receta.getIdreceta()+"'");
			
			Calificacion calificacion = (Calificacion) query.uniqueResult();
			session.getTransaction().commit();
			return calificacion;
		}
		catch(Exception ex){
			if(session.getTransaction().isActive()){
				session.getTransaction().rollback();
			}
			throw ex;
		}
	}
}
