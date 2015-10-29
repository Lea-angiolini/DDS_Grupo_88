package Database;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Receta;
import ObjetosDB.Usuario;
import ObjetosDB.itemsABuscar;

public class DAORecetas extends DAOGenerico<Receta,Integer> {
	
	public DAORecetas(Session session) {
		// TODO Auto-generated constructor stub
		super(session);
	}
	
	@Override
	public void saveOrUpdate(Receta entity) throws DBExeption {
		// TODO Auto-generated method stub
		entity.calcularCalorias();
		super.saveOrUpdate(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Receta> recetasDeUsuario(Usuario user) throws DBExeption{
		
		//Session session = sessionFactory.getCurrentSession();

		 try {
			session.beginTransaction();
			Query query = session.createQuery("from Receta r where r.creador.username = :user")
					.setParameter("user", user.getUsername());
			
			List<Receta> recetas = (List<Receta>)query.list();
			session.getTransaction().commit();
			return recetas;
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
	
	
	public List<Receta> buscarReceta (itemsABuscar items) throws DBExeption{
		//Session session = sessionFactory.getCurrentSession();
		
		try{
		session.beginTransaction();
		String stringQuery = "from Receta r where (r.caloriasTotales between :caloriasMin AND :caloriasMax) ";
		
		if(items.getDificultad() != null){
			stringQuery += " and (r.dificultad.idDificultad= "+items.getDificultad().getIdDificultad()+") ";
			//query.setParameter("idDificultad", items.getDificultad().getIdDificultad());
		}
		
		if(items.getIngredientePrincipal() != null){
			stringQuery += "and (r.ingredientePrincipal.idIngrediente = "+items.getIngredientePrincipal().getId()+") ";
			//query.setParameter("idIngrediente", items.getIngredientePrincipal().getIdIngrediente());
		}
		
		if (items.getTemporada() != null) {
			stringQuery += " and (r.temporada.idTemporada = "+items.getTemporada().getIdTemporada()+" ) ";
		}
		
		if (items.getCalificacion() != null) {
			stringQuery += " and ((r.puntajeTotal / r.vecesCalificada) = "+items.getCalificacion().getValor()+") "; //TOFIX if vecesCalificada = 0?
		}
		
		if (items.getGrupoAlimenticio() != null){
			stringQuery += " and (r.ingredientePrincipal.idTipoIngrediente.grupoQuePertenece.idGrupoAlim = "+ items.getGrupoAlimenticio().getIdGrupoAlim()+") ";
		}
		
		Query query = session.createQuery(stringQuery)
				.setParameter("caloriasMin", items.getCaloriasMin())
				.setParameter("caloriasMax", items.getCaloriasMax());
		
		List<Receta> recetas = (List<Receta>) query.list();
		
		session.getTransaction().commit();
		return recetas;
		
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
