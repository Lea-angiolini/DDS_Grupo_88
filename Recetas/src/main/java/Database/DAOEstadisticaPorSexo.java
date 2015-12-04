package Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Estadistico;
import ObjetosDB.Sexo;

public class DAOEstadisticaPorSexo extends DAOEstadistica{
	
	public DAOEstadisticaPorSexo(Session session) {
		super(session);
		
	}
	@Override
	public ArrayList<Estadistico> obtenerEstadistica(int dias) throws Exception{
		
		this.dias = dias;
		ArrayList<Estadistico> estadisticos = new ArrayList<Estadistico>();
		Query query;
		try{
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
		catch(Exception ex){
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			session.flush();
			throw ex;
		}
	}
	private String consulta(int dias, int sexo){
		return "select new ObjetosDB.Estadistico(STR(h.usuario.sexo.descripcion),concat(STR(h.receta.tipoReceta.descripcion),STR(count(*)))) from Historial h "+
				"where fecha > '"+fechaAterior(dias)+"' and h.usuario.sexo.idSexo = '"+sexo+"' group by h.usuario.sexo, h.receta.tipoReceta.descripcion "+
				"order by count(*) desc";
	}
}
