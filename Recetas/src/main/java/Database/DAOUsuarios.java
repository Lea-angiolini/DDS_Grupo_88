package Database;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Usuario;

public class DAOUsuarios extends DAOGenerico<Usuario, String>{
	
	public DAOUsuarios(Session session) {
		super(session);
	}
	
	public Usuario loguear(String username, String pass){
		//Session session = sessionFactory.getCurrentSession();
	
		session.beginTransaction();
		Query query = session.createQuery("from Usuario u where u.username = :user and u.password = :pass ")
				.setParameter("user", username)
				.setParameter("pass", pass);
		
		Object resultado = query.uniqueResult();
		session.getTransaction().commit();
		if (resultado != null)
			return (Usuario) resultado;
		return null;
	}
}
