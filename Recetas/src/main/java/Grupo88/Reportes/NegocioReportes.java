package Grupo88.Reportes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import master.Negocio;
import objetosWicket.SesionUsuario;

import org.hibernate.Session;

import Database.DAOReportes;
import ObjetosDB.Confirmacion;
import ObjetosDB.Historial;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class NegocioReportes extends Negocio implements Serializable {

	private static final long serialVersionUID = -309099855638170941L;
	private DAOReportes daoReportes;
	
	public NegocioReportes(SesionUsuario sesion) {
		super(sesion);
		daoReportes = new DAOReportes(sesion.getSessionDB(), sesion.getUsuarioActual());
	}
	
	public DAOReportes getDaoReportes() {
		return daoReportes;
	}
	
	public ArrayList<Receta> recetasCreadas(){
		try {
			return (ArrayList<Receta>) daoReportes.recetasCreadas();
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return new ArrayList<Receta>();
		}
	}
	
	public ArrayList<Historial> recetasmasconsultadas(String fechaDesde, String fechaHasta){
		try {
			return (ArrayList<Historial>) daoReportes.recetasmasconsultadas(fechaDesde, fechaHasta);
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return new ArrayList<Historial>();
		}
	}
	
	public ArrayList<Confirmacion> recetasConfirmadas(String calMin, String calMax){
		try {
			return (ArrayList<Confirmacion>) daoReportes.recetasConfirmadas(calMin, calMax);
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return new ArrayList<Confirmacion>();
		}
	}
}
