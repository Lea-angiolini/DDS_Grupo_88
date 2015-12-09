package Database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Confirmacion;
import ObjetosDB.Historial;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class DAOReportes {
	
	private Session session;
	private Usuario user;
	public DAOReportes(Session session, Usuario user) {
		this.session = session;
		this.user = user;
	}
	
	public List<Historial> recetasmasconsultadas(String desde, String hasta) throws Exception{
		List<Historial> listaHistorial = null;
		Query query;
		try{
			session.beginTransaction();
			query = session.createQuery("from Historial h where fecha >= '"+desde+"' and fecha <= '"+hasta+"' and usuario.username = '"+user.getUsername()+"'");
			listaHistorial = query.list();
			session.getTransaction().commit();
		}
		catch(Exception ex){
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			session.flush();
			throw ex;
		}
		
		return listaHistorial;
	}
	
	public List<Receta> recetasCreadas() throws Exception{
		List<Receta> listaReceta = null;
		Query query;
		try{
			session.beginTransaction();
			query = session.createQuery("from Receta r where r.creador.username = '"+user.getUsername()+"'");
			listaReceta = query.list();
			session.getTransaction().commit();
		}
		catch(Exception ex){
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			session.flush();
			throw ex;
		}
		
		return listaReceta;
	}
	
	public List<Confirmacion> recetasConfirmadas(String calMin, String calMax) throws Exception{
		List<Confirmacion> listaReceta = null;
		Query query;
		try{
			session.beginTransaction();
			query = session.createQuery("from Confirmacion c where c.user.username = '"+user.getUsername()+"' and "+
			"c.receta.caloriasTotales >= '"+calMin+"' and c.receta.caloriasTotales <= '"+calMax+"'");
			listaReceta = query.list();
			session.getTransaction().commit();
		}
		catch(Exception ex){
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			session.flush();
			throw ex;
		}
		
		return listaReceta;
	}
}