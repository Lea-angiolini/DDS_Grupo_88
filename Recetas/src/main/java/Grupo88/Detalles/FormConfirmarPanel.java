package Grupo88.Detalles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import master.ErrorPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import Database.DAOConfirmar;
import Database.DAOTipoReceta;
import ObjetosDB.Confirmacion;
import ObjetosDB.Receta;
import ObjetosDB.TipoReceta;
import ObjetosDB.Usuario;

public class FormConfirmarPanel extends Panel{

	private static final long serialVersionUID = 5866866909620842478L;

	public FormConfirmarPanel(String id,Session sess, Usuario use, Receta rec) {
		super(id);
		add(new FormConfirmar(id, sess, use, rec));
	}

	
	private class FormConfirmar extends Form{
	
		private static final long serialVersionUID = 3482226522671170037L;
		private Confirmacion confirmacion;
		private DAOTipoReceta daoTipoReceta;
		private ArrayList<TipoReceta> todosTipoReceta;
		private final Session session;
		private Receta receta;
		private Usuario user;
		public FormConfirmar(String id,Session sess, Usuario use, Receta rec) {
			super(id);
			
			this.user = use;
			this.receta = rec;
			this.session = sess;
			confirmacion = new Confirmacion();
			daoTipoReceta = new DAOTipoReceta(session);
	
			try {
				
				todosTipoReceta= new ArrayList<TipoReceta>(daoTipoReceta.findAll());
	
			} catch (Exception e1) {
				setResponsePage(ErrorPage.ErrorCargaDatos());
				return;
			}
			
			add(new RadioChoice("comida", new PropertyModel(confirmacion, "tipoReceta"), todosTipoReceta,new ChoiceRenderer("descripcion","idTipoReceta")));
			add(new Button("confirmar"){
				
				private static final long serialVersionUID = 8149758093736016771L;
	
				public void onSubmit() {
					super.onSubmit();
					final PageParameters pars = new PageParameters();
					pars.add("idReceta",receta.getIdreceta());
					DAOConfirmar daoConfirmar = new DAOConfirmar(session);
					confirmacion.setReceta(receta);
					confirmacion.setUser(user);
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					confirmacion.setFecha(dateFormat.format(new Date()));
						
						try {
							daoConfirmar.save(confirmacion);
						} 
						
						catch (ConstraintViolationException e) {
							e.printStackTrace();
							setResponsePage(ErrorPage.ErrorEnLaDB());
							
						} 
						
						catch (javax.validation.ConstraintViolationException e) {
							e.printStackTrace();
							setResponsePage(ErrorPage.ErrorEnLaDB());
						}
						
						catch (Exception e) {
							e.printStackTrace();
							setResponsePage(ErrorPage.ErrorRandom());
						}
					
					setResponsePage(DetalleDeReceta.class,pars);
				}
			});
	
		}
	}
}