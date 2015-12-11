package Grupo88.Detalles;

import java.util.Arrays;

import javax.swing.JOptionPane;

import master.ErrorPage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import Database.DAOCalificacion;
import Database.DAOConfirmar;
import ObjetosDB.Calificacion;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class FormCalificarPanel extends Panel{

	private static final long serialVersionUID = -3061139119781597354L;

	public FormCalificarPanel(String id, Session sess, Receta rec, Usuario use) {
		super(id);
		add(new FormCalificar(id, sess, rec, use));
	}
	
	private class FormCalificar extends Form{
	
		private static final long serialVersionUID = -7463688006174588993L;
		
		private DAOCalificacion daoCalificacion;
		private DAOConfirmar daoConfirmar;
		private Calificacion calificacion;
		private Session session;
		private Receta receta;
		private Usuario user;
		
		public FormCalificar(String id, Session sess, Receta rec, Usuario use) {
			super(id);
			
			this.session = sess;
			this.receta = rec;
			this.user = use;
			daoCalificacion = new DAOCalificacion(session);
			daoConfirmar = new DAOConfirmar(session);
			try {
				calificacion = daoCalificacion.calificacionDe(receta, user);
				
				if(calificacion == null){
					calificacion = new Calificacion();
					calificacion.setReceta(receta);
					calificacion.setUserCalificador(user);
				}
			} catch (Exception e) {
				setResponsePage(ErrorPage.ErrorRandom());
	
			}
			
			EmptyPanel botonCal;
			try {
				if(!daoConfirmar.userConfirmo(receta, user)){
					add(new EmptyPanel("calificacion"));
					add(botonCal = new EmptyPanel("confCalificacion"));
					botonCal.setVisible(false);
				}
				else{
					
					
					RadioChoice<Integer> choices = new RadioChoice<Integer>("calificacion",new PropertyModel<Integer>(calificacion,"calificacion"),Arrays.asList(1,2,3,4,5));
					choices.setSuffix("");
					add(choices);
				
				add(new Button("confCalificacion"){
	
					private static final long serialVersionUID = -6871206486314240822L;
	
					@Override
					public void onSubmit() {
						super.onSubmit();
						try {
							daoCalificacion.saveOrUpdate(calificacion);
						} catch (ConstraintViolationException e) { 
							e.printStackTrace();
							setResponsePage(ErrorPage.ErrorEnLaDB());
							
						} catch (javax.validation.ConstraintViolationException e) {
							e.printStackTrace();
							setResponsePage(ErrorPage.ErrorEnLaDB());
							
						} catch (Exception e) {
							e.printStackTrace();
							setResponsePage(ErrorPage.ErrorRandom());
						}
					}
				});
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				setResponsePage(ErrorPage.ErrorRandom());
			}
		}
	}
}