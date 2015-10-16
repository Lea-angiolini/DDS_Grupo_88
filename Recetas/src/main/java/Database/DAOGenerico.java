package Database;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOGenerico<T, ID extends Serializable> implements IDAOGenerico<T, ID> {
	
	SessionFactory sessionFactory;
	Session session;
	
	public DAOGenerico() {
		sessionFactory=HibernateUtil.getSessionFactory();
		session = sessionFactory.openSession();
	}
	
	public void closeSession(){
		if (session != null){
			session.close();
		}
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
	 public void saveOrUpdate(T entity) throws DBExeption {
		 Session session = sessionFactory.getCurrentSession();
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
	 public T get(ID id) throws DBExeption {
		 Session session = sessionFactory.getCurrentSession();
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
		Session session = sessionFactory.getCurrentSession();
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
		Session session = sessionFactory.getCurrentSession();
		try {
	
			Query query = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e");
			@SuppressWarnings("unchecked")
			List<T> entities = (List<T>) query.list();
	
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
