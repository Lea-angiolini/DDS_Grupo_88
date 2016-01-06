package Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Estadistico;
import ObjetosDB.Sexo;

public class DAOEstadisticaPorSexo extends DAOEstadistica implements Serializable{
	

	private static final long serialVersionUID = -7953169414586587316L;
	public DAOEstadisticaPorSexo(Session session) {
		super(session);
	}
	
	@Override
	public String descripcionEst() {
		return "Estadisticas por Sexo";
	}
	
	@Override
	public ArrayList<Estadistico> obtenerEstadistica(int dias) throws Exception{
		
		this.dias = dias;
		ArrayList<Estadistico> estadisticos = new ArrayList<Estadistico>();
		Query query;
		
		session.beginTransaction();
		query = session.createQuery("from Sexo s");
		List<Sexo> sexos = query.list();
		session.getTransaction().commit();
			
		for(Sexo s : sexos){
			session.beginTransaction();
			 query = session.createQuery(consulta(dias, s.getIdSexo()));
			 query.setMaxResults(1);
			 estadisticos.addAll(((ArrayList<Estadistico>) query.list()));
			session.getTransaction().commit();
		}
			
		return estadisticos;
	}
	private String consulta(int dias, int sexo){
		return "select new ObjetosDB.Estadistico(STR(h.usuario.sexo.descripcion),STR(h.receta.tipoReceta.descripcion)) from Historial h "+
				"where fecha > '"+fechaAterior(dias)+"' and h.usuario.sexo.idSexo = '"+sexo+"' group by h.usuario.sexo, h.receta.tipoReceta.descripcion "+
				"order by count(*) desc";
	}
}
