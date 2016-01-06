package Database;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class DAOGenerico<T, ID extends Serializable> implements IDAOGenerico<T, ID>, Serializable {
	

	private static final long serialVersionUID = 7802092408107142015L;
	Session session;
	
	public DAOGenerico(Session session) {
		this.session = session;
	}
	
	public void limpiarSesion(){
		//session.clear();
	}
	
	@Override
	public T create() throws Exception {
		try {
			return getEntityClass().newInstance();
			}
		catch (InstantiationException ex) {
			throw new RuntimeException(ex);
		}
		catch (IllegalAccessException ex) {
			throw new RuntimeException(ex);
	        } 
		catch (RuntimeException ex) {
	         throw ex;
	          } 
		catch (Exception ex) {
	          throw new RuntimeException(ex);
	          }
	}
	 
	 @Override
	 public void saveOrUpdate(T entity) throws javax.validation.ConstraintViolationException,org.hibernate.exception.ConstraintViolationException, Exception {
		 	
		 session.beginTransaction();
		 session.saveOrUpdate(entity);
		 session.getTransaction().commit();
	 	}
	 
	 public void save(T entity) throws javax.validation.ConstraintViolationException,org.hibernate.exception.ConstraintViolationException, Exception {

		 session.beginTransaction();
		 session.save(entity);
		 session.getTransaction().commit();
	 	}
	 
	 @Override
	 public T get(ID id) throws Exception {

		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		T entity = (T) session.get(getEntityClass(), id);
		session.getTransaction().commit();
	 
		return entity;
	 }
	 
	
	@Override
	public void delete(ID id) throws Exception{

		session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		T entity = (T) session.get(getEntityClass(), id);
	
		if (entity == null) {
			throw new javax.validation.ConstraintViolationException("Los datos a borrar ya no existen", null);
		}
	
		session.delete(entity);
		session.getTransaction().commit();
	 }
	
	@Override
	public List<T> findAll() throws Exception{

		session.beginTransaction();
		Query query = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e");
		@SuppressWarnings("unchecked")
		List<T> entities = (List<T>) query.list();
		session.getTransaction().commit();
		return entities;
		
	 	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
