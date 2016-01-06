package Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Receta;
import ObjetosDB.Usuario;
import ObjetosDB.itemsABuscar;

public class DAORecetas extends DAOGenerico<Receta,Integer> implements Serializable{
	
	private static final long serialVersionUID = -3242079439533262957L;

	public DAORecetas(Session session) {
		super(session);
	}
	
	@Override
	public void saveOrUpdate(Receta entity) throws Exception {
		entity.calcularCalorias();
		super.saveOrUpdate(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Receta> recetasDeUsuario(Usuario user) throws Exception{
		
		session.beginTransaction();
		Query query = session.createQuery("from Receta r where r.creador.username = :user")
					.setParameter("user", user.getUsername());
			
		List<Receta> recetas = (List<Receta>)query.list();
		session.getTransaction().commit();

		return recetas;
		 
	}
	
	
	public List<Receta> buscarReceta (itemsABuscar items) throws Exception{

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
			stringQuery += " and (select cast(sum(c.calificacion)/count(c.calificacion) as int) from Calificacion c where c.key.receta.idreceta = r.idreceta) = "+items.getCalificacion()+") "; //TOFIX if vecesCalificada = 0?
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
	
	public List<Receta> ultimasConsultadas(Usuario user, int cantidad) throws Exception{
		return recetas_in("select h.receta.idreceta FROM Historial h where h.usuario.username = '"+user.getUsername()+"'" +
							"order by h.idHistorial desc",cantidad);
	}
	public List<Receta> mejoresCalificadas(int cantidad) throws Exception {
		return recetas_in("select c.key.receta.idreceta from Calificacion c "+ 
								"group by c.key.receta.idreceta order by sum(c.calificacion) desc", cantidad);
	}
	
	public List<Receta> ultimasConfirmadas(Usuario user, int cantidad) throws Exception {
		return recetas_in("select c.receta.idreceta from Confirmacion c where c.user.username = '"+user.getUsername()+"' "+
	                        "order by c.IdConfirmacion desc",cantidad);
	}
	public List<Receta> recetas_in(String select, int cantidad) throws Exception {
		
		List<Receta> recetas;
		
		session.beginTransaction();
		Query query = session.createQuery("from Receta r where r.idreceta in ("+select+")");
		query.setMaxResults(10);
	
		recetas = (List<Receta>) query.list();
		session.getTransaction().commit();
	
		return recetas;
	}
	
	public void agregarAHistorial(int idReceta, String username) throws Exception{
		
		session.beginTransaction();
		
		Query query = session.createSQLQuery("INSERT INTO Grupo88.confirmadas(fecha, idReceta, usuario) "+
                               "VALUES (current_date(), :idReceta, '"+username+"');");
		query.setParameter("idReceta", idReceta)/*.setParameter("user", username)*/;
		query.executeUpdate();
	
		session.getTransaction().commit();

	}
	
	/*public ArrayList<Receta> recetasPara(Usuario user){
		
	}*/
}
