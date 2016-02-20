package Database;

import java.io.Serializable;

import objetosWicket.SesionUsuario;

import org.hibernate.HibernateException;

public class ManejadorExepciones implements Serializable {

	private static final long serialVersionUID = -3032602191803325077L;
	private SesionUsuario sesion;
	
	public ManejadorExepciones(SesionUsuario sesion) {
		this.sesion = sesion;
	}
	
	public String tratarExcepcion(Exception exepcion){
		
		if(exepcion instanceof javax.validation.ConstraintViolationException){
			javax.validation.ConstraintViolationException cve;
			cve = (javax.validation.ConstraintViolationException) exepcion;
			
			try {
				 if (sesion.getSessionDB().getTransaction().isActive()) {
					 sesion.getSessionDB().getTransaction().rollback();
				 }
			 } 
			 catch (HibernateException exc) {
				sesion.reiniciarSesion();
			 }
			
			if(cve.getConstraintViolations() == null)
				return cve.getMessage();
			
			return cve.getConstraintViolations().iterator().next().getMessage();
		}
		
		sesion.reiniciarSesion();
		return "Error irrecuperable";
	}
}
