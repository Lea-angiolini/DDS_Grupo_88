package Database;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Estadistico;

public class DAOEstadisticasPorDificultad extends DAOEstadistica implements Serializable{
	
	private static final long serialVersionUID = -558313456891704103L;

	public DAOEstadisticasPorDificultad(Session session) {
		super(session);
	}
	
	@Override
	public ArrayList<Estadistico> obtenerEstadistica(int dias) throws Exception {
		
		ArrayList<Estadistico> estadisticos;

		session.beginTransaction();
		Query query = session.createQuery("select new ObjetosDB.Estadistico(STR(h.receta.dificultad.dificultad),STR(count(*))) from Historial h "
					+"where fecha > '"+fechaAterior(dias)+ "' group by h.receta.dificultad.dificultad order by h.receta.dificultad.idDificultad");
		estadisticos = (ArrayList<Estadistico>) query.list();
		session.getTransaction().commit();
			
		return estadisticos;
	}
	
	@Override
	public String descripcionEst() {
		return "Estadisticas por dificultad";
	}

}
