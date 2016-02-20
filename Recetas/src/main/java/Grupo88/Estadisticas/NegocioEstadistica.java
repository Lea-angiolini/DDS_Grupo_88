package Grupo88.Estadisticas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import master.Negocio;
import objetosWicket.SesionUsuario;
import Database.DAOEstadistica;
import Database.DAOEstadisticaPorSexo;
import Database.DAOEstadisticasPorDificultad;
import Database.DAORecetasMasConsultadas;
import ObjetosDB.Estadistico;

public class NegocioEstadistica extends Negocio implements Serializable{

	private static final long serialVersionUID = -3955783136211330307L;
	private DAOEstadistica dao;
	
	public static NegocioEstadistica porSexo(SesionUsuario sesion){
		return new NegocioEstadistica(sesion, new DAOEstadisticaPorSexo(sesion.getSessionDB()));
	}
	
	public static NegocioEstadistica porDificultad(SesionUsuario sesion){
		return new NegocioEstadistica(sesion, new DAOEstadisticasPorDificultad(sesion.getSessionDB()));
	}
	
	public static NegocioEstadistica recetasMasconsultadas(SesionUsuario sesion){
		return new NegocioEstadistica(sesion, new DAORecetasMasConsultadas(sesion.getSessionDB(),10));
	}
	
	public NegocioEstadistica(SesionUsuario sesion, DAOEstadistica dao) {
		
		super(sesion);
		this.dao = dao;
	}
	
	public List<Integer> getFechas(){
		return Arrays.asList(7,30);
	}

	public ArrayList<Estadistico> obtenerEstadistica(int dias){
		try {
			return dao.obtenerEstadistica(dias);
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return null;
		}
	}
	
	public String descripcionEst(){
		return dao.descripcionEst();
	}
}
