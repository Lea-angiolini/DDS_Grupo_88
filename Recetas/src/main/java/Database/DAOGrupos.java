package Database;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Grupo;
import ObjetosDB.Usuario;

public class DAOGrupos extends DAOGenerico<Grupo,Integer> implements Serializable{

	private static final long serialVersionUID = 4630445719633300126L;

	public DAOGrupos(Session session) {
		// TODO Auto-generated constructor stub
		super(session);
	}
	
	public List<Grupo> gruposde(Usuario user) throws Exception{
		
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Grupo g where :user in elements(g.usuarios)");
			query.setParameter("user", user);
			
			List<Grupo> grupos = query.list();
			session.getTransaction().commit();
			
			return grupos;
		}
		catch(Exception ex){
			if(session.getTransaction().isActive()){
				session.getTransaction().rollback();
			}
			throw ex;	
			
		}
	}
}
