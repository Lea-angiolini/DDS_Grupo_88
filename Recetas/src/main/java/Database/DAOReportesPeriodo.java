package Database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Estadistico;
import ObjetosDB.Historial;
import ObjetosDB.Receta;
import ObjetosDB.Sexo;
import ObjetosDB.Usuario;

public class DAOReportesPeriodo extends DAOReportes{
	
	private Usuario user;
	public DAOReportesPeriodo(Session session, Usuario user) {
		super(session);
		this.user = user;
	}
	
	@Override
	public List<Historial> recetasmasconsultadas(String desde, String hasta) throws Exception {
		
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
}
