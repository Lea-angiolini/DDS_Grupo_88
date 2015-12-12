package Grupo88.Estadisticas;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import Database.DAOEstadistica;
import Database.DAOEstadisticaPorSexo;
import Database.DAOEstadisticasPorDificultad;
import Database.DAORecetasMasConsultadas;

public class NegocioEstadistica {
	private Session session;
	private DAOEstadistica dao;
	
	public static NegocioEstadistica porSexo(Session session){
		return new NegocioEstadistica(session, new DAOEstadisticaPorSexo(session));
	}
	
	public static NegocioEstadistica porDificultad(Session session){
		return new NegocioEstadistica(session, new DAOEstadisticasPorDificultad(session));
	}
	
	public static NegocioEstadistica recetasMasconsultadas(Session session){
		return new NegocioEstadistica(session, new DAORecetasMasConsultadas(session,10));
	}
	
	public NegocioEstadistica(Session session, DAOEstadistica dao) {
		
		this.session = session;
		this.dao = dao;
	}
	
	public List<Integer> getFechas(){
		return Arrays.asList(70,30);
	}
	
	public DAOEstadistica getDAO() {
		return dao;
	}
}
