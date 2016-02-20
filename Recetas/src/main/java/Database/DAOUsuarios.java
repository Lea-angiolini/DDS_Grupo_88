package Database;

import java.io.Serializable;

import javax.validation.ConstraintViolationException;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Usuario;

public class DAOUsuarios extends DAOGenerico<Usuario, String> implements Serializable{
	
	private static final long serialVersionUID = -6177744167687215376L;

	public DAOUsuarios(Session session) {
		super(session);
	}
	
	public Usuario loguear(String username, String pass) throws Exception{

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
	private void validacionesSave(Usuario entity) throws org.hibernate.exception.ConstraintViolationException{
		Query query;
		query = session.createQuery("from Usuario u where username = '" +entity.getUsername()+"'");
		if(query.list().size() != 0){
			throw new javax.validation.ConstraintViolationException("El usuario ya existe", null);
		}
		query = session.createQuery("from Usuario u where email = '" +entity.getEmail()+"'");
		if(query.list().size() != 0){
			throw new javax.validation.ConstraintViolationException("El mail ya esta registrado", null);
		}
	}
	
	private void validacionesSaveOrUpdate(Usuario entity) throws org.hibernate.exception.ConstraintViolationException{
		Query query;
		query = session.createQuery("from Usuario u where email = '" +entity.getEmail()+"' and username <> '" 
										+entity.getUsername()+"'");
		if(query.list().size() != 0){
			throw new javax.validation.ConstraintViolationException("El mail ya esta registrado", null);
		}
	}
	
	@Override
	public void saveOrUpdate(Usuario entity)
			throws ConstraintViolationException,
			org.hibernate.exception.ConstraintViolationException, Exception {
		
		validacionesSaveOrUpdate(entity);
		super.saveOrUpdate(entity);
	}
	@Override
	public void save(Usuario entity) throws ConstraintViolationException,
			org.hibernate.exception.ConstraintViolationException, Exception {
		
		validacionesSave(entity);
		super.save(entity);
	}
}
