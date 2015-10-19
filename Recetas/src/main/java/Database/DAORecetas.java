package Database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class DAORecetas extends DAOGenerico<Receta,Integer> {
	
	public DAORecetas() {
		// TODO Auto-generated constructor stub
		super();
	}
	@SuppressWarnings("unchecked")
	public List<Receta> recetasDeUsuario(Usuario user) throws DBExeption{
		
		Session session = sessionFactory.getCurrentSession();

		 try {
			Query query = session.createQuery("from Receta r where r.creador.username = :user")
					.setParameter("user", user.getUsername());
			
			return (List<Receta>)query.list();
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
}
