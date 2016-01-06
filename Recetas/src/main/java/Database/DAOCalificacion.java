package Database;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Calificacion;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class DAOCalificacion extends DAOGenerico<Calificacion, Calificacion.Key> implements Serializable{

	private static final long serialVersionUID = -4861796335905934635L;

	public DAOCalificacion(Session session) {
		super(session);
	}
	
	public Calificacion calificacionDe(Receta receta, Usuario user) throws Exception{
		
		session.beginTransaction();
		Query query = session.createQuery("from Calificacion where key.userCalificador = '"+user.getUsername()
											+"' and key.receta.idreceta = '"+receta.getIdreceta()+"'");
			
		Calificacion calificacion = (Calificacion) query.uniqueResult();
		session.getTransaction().commit();
		return calificacion;
	}
}
