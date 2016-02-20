package Database;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Confirmacion;
import ObjetosDB.Historial;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class DAOReportes implements Serializable {

	private static final long serialVersionUID = 1647682211098490174L;
	private Session session;
	private Usuario user;
	public DAOReportes(Session session, Usuario user) {
		this.session = session;
		this.user = user;
	}
	
	@SuppressWarnings("unchecked")
	public List<Historial> recetasmasconsultadas(String desde, String hasta) throws Exception{
		List<Historial> listaHistorial = null;
		Query query;

		session.beginTransaction();
		query = session.createQuery("from Historial h where fecha >= '"+desde+"' and fecha <= '"+hasta+"' and usuario.username = '"+user.getUsername()+"'");
		listaHistorial = query.list();
		session.getTransaction().commit();
		
		return listaHistorial;
	}
	
	@SuppressWarnings("unchecked")
	public List<Receta> recetasCreadas() throws Exception{
		List<Receta> listaReceta = null;
		Query query;

		session.beginTransaction();
		query = session.createQuery("from Receta r where r.creador.username = '"+user.getUsername()+"'");
		listaReceta = query.list();
		session.getTransaction().commit();
		
		return listaReceta;
	}
	
	@SuppressWarnings("unchecked")
	public List<Confirmacion> recetasConfirmadas(String calMin, String calMax) throws Exception{
		List<Confirmacion> listaReceta = null;
		Query query;

		session.beginTransaction();
		query = session.createQuery("from Confirmacion c where c.user.username = '"+user.getUsername()+"' and "+
		"c.receta.caloriasTotales >= '"+calMin+"' and c.receta.caloriasTotales <= '"+calMax+"'");
		listaReceta = query.list();
		session.getTransaction().commit();
		
		return listaReceta;
	}
}