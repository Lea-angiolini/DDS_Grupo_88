package Database;

import java.io.Serializable;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import ObjetosDB.Estadistico;

public class DAORecetasMasConsultadas extends DAOEstadistica implements Serializable {

	private static final long serialVersionUID = -1020776969304512072L;
	private int cantidad;
	public DAORecetasMasConsultadas(Session session, int cantidad) {
		super(session);
		this.cantidad = cantidad;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Estadistico> obtenerEstadistica(int dias) throws Exception {

		ArrayList<Estadistico> estadisticos;

		session.beginTransaction();
		Query query = session.createQuery("select new ObjetosDB.Estadistico(STR(''),STR(h.receta.nombre)) from Historial h "
				+"where fecha > '"+fechaAterior(dias)+ "' group by h.receta.nombre order by count(*) desc");
		query.setMaxResults(cantidad);
		estadisticos = (ArrayList<Estadistico>) query.list();
			
		for(int i = 0; i < estadisticos.size(); i++){
			estadisticos.get(i).setDescripcion(String.valueOf(i+1));
		}
		session.getTransaction().commit();
			
		return estadisticos;
		
	}
	
	@Override
	public String descripcionEst() {
		return "Estadisticas recetas mas consultadas";
	}
}
