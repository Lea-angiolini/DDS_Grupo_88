package Database;

import javax.swing.JOptionPane;

import objetosWicket.SesionUsuario;

import org.hibernate.HibernateException;

public class ManejadorExepciones {

	private SesionUsuario sesion;
	
	public ManejadorExepciones(SesionUsuario sesion) {
		this.sesion = sesion;
	}
	
	public String tratarExcepcion(Exception exepcion){
		
		JOptionPane.showMessageDialog(null, exepcion.getClass()+" /n"+ exepcion.getMessage());
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
