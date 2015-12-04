package Database;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;

import ObjetosDB.Estadistico;

public abstract class DAOEstadistica implements Serializable{
	
	private static final long serialVersionUID = -6992049224059077581L;
	protected Session session;
	protected int dias;
	
	public DAOEstadistica(Session session) {
		this.session = session;
	}
	
	public abstract ArrayList<Estadistico> obtenerEstadistica(int dias) throws Exception;
	
	public abstract String descripcionEst();
	
	protected String fechaAterior(int dias){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1 * dias);
		Date dateBefore30Days =  cal.getTime();
		return dateFormat.format(dateBefore30Days).toString();
	}
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}	
}
