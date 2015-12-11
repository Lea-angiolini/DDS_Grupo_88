package Database;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.HibernateException;
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
	public T create() throws DBExeption {
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
		 	
		 try {
			 session.beginTransaction();
			 session.saveOrUpdate(entity);
			 session.getTransaction().commit();
		 } 		 
		 catch (javax.validation.ConstraintViolationException cve) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
				 }
			 } 
			 catch (HibernateException exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
				 throw new Exception(exc.getLocalizedMessage());
			 }
			 throw cve;
		 } 
		 catch (org.hibernate.exception.ConstraintViolationException cve) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
				 }
			 }
			 catch (HibernateException exc) {
			     
				 exc.printStackTrace();
				 throw new Exception(exc.getMessage());
				 
			 }
			throw cve;
			 
		 } 
		 catch (RuntimeException ex) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
				 throw new DBExeption(exc);
			 }
			 throw new Exception(ex);
		 } 
		 catch (Exception ex) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
				 throw new Exception(exc);
			 }
			 throw new Exception(ex);
		 }
	 	}
	 
	 public void save(T entity) throws javax.validation.ConstraintViolationException,org.hibernate.exception.ConstraintViolationException, Exception {

		 try {
			 session.beginTransaction();
			 session.save(entity);
			 session.getTransaction().commit();
		 } 		 
		 catch (javax.validation.ConstraintViolationException cve) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
				 }
			 } 
			 catch (HibernateException exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
				 
				 throw new Exception(exc);
			 }
			 limpiarSesion();
			 throw cve;
		 } 
		 catch (org.hibernate.exception.ConstraintViolationException cve) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
				 }
			 }
			 catch (HibernateException exc) {
			     
				 exc.printStackTrace();
				 throw new Exception(exc.getMessage());
			 }
		
			limpiarSesion();
			throw cve;
			 
		 } 
		 catch (RuntimeException ex) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
				 throw new DBExeption(exc);
			 }
			 throw new Exception(ex);
		 } 
		 catch (Exception ex) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
				 throw new Exception(exc);
			 }
			 throw new Exception(ex);
		 }
	 	}
	 
	 @Override
	 public T get(ID id) throws DBExeption {
		 //Session session = sessionFactory.getCurrentSession();
		 try {
			 session.beginTransaction();
			 @SuppressWarnings("unchecked")
			T entity = (T) session.get(getEntityClass(), id);
			 session.getTransaction().commit();
	 
			 return entity;
		 } 
		 catch (javax.validation.ConstraintViolationException cve) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw new DBExeption(cve);
		 } 
		 catch (org.hibernate.exception.ConstraintViolationException cve) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw new DBExeption(cve);
		 } 
		 catch (RuntimeException ex) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
				 
			 }
			 throw ex;
		 } 
		 catch (Exception ex) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw new RuntimeException(ex);
		 }
	 	}
	 
	
	@Override
	public void delete(ID id) throws DBExeption {
		//Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			T entity = (T) session.get(getEntityClass(), id);
			if (entity == null) {
				throw new DBExeption(null, "Los datos a borrar ya no existen");
			}
			session.delete(entity);
			session.getTransaction().commit();
		} 
		 catch (javax.validation.ConstraintViolationException cve) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw new DBExeption(cve);
		 } 
		 catch (org.hibernate.exception.ConstraintViolationException cve) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw new DBExeption(cve);
		 } 
		 catch (RuntimeException ex) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw ex;
		 } 
		 catch (Exception ex) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw new RuntimeException(ex);
		 }
	 	}
	
	@Override
	public List<T> findAll() throws DBExeption {
		//Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e");
			@SuppressWarnings("unchecked")
			List<T> entities = (List<T>) query.list();
			session.getTransaction().commit();
			return entities;} 
		 catch (javax.validation.ConstraintViolationException cve) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw new DBExeption(cve);
		 } 
		 catch (org.hibernate.exception.ConstraintViolationException cve) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
					 
				 }
			 } 
			 catch (Exception exc) {
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw new DBExeption(cve);
		 } 
		 catch (RuntimeException ex) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
				 }
			 } 
			 catch (Exception exc) {
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw ex;
		 } 
		 catch (Exception ex) {
			 try {
				 if (session.getTransaction().isActive()) {
					 session.getTransaction().rollback();
				 }
			 } 
			 catch (Exception exc) {
				 exc.printStackTrace();
				 System.out.println("Falló al hacer un rollback");
			 }
			 throw new RuntimeException(ex);
		 }
	 	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
