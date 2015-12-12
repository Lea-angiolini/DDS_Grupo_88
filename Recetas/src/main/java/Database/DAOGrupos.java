package Database;

import java.io.Serializable;
import java.util.List;

import javax.swing.JOptionPane;
import javax.validation.ConstraintViolationException;

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
	
	private void validacion(Grupo entity) throws org.hibernate.exception.ConstraintViolationException{
		Query query;
		query = session.createQuery("from Grupo u where nombre = '" +entity.getNombre()+"'");
		if(query.list().size() != 0){
			throw new org.hibernate.exception.ConstraintViolationException("El grupo con este nombre ya existe", null, "");
		}
	}
	
	@Override
	public void save(Grupo entity) throws ConstraintViolationException,
			org.hibernate.exception.ConstraintViolationException, Exception {
		
		validacion(entity);
		super.save(entity);
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
	
	
	public List<Grupo> gruposCon(String nom) throws Exception{
		
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Grupo g where lower(nombre) like :nom");
			query.setParameter("nom", "%" + nom.toLowerCase() +"%");
			
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
