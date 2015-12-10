package Grupo88.Detalles;

import java.util.ArrayList;

import master.ErrorPage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import Database.DAOGrupos;
import ObjetosDB.Grupo;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class FormCompartirPanel extends Panel{
	
	public FormCompartirPanel(String id, Session sess, Usuario use, Receta rec) {
		super(id);
		
		add(new FormCompartir(id, sess, use, rec));
	}
	private class FormCompartir extends Form{
		
		private DAOGrupos daoGrupos;
		private Session session;
		private Usuario user;
		private Receta receta;
		
		public FormCompartir(String id, Session sess, Usuario use, Receta rec) {
			super(id);
			this.session = sess;
			this.user = use;
			this.receta = rec;
			daoGrupos = new DAOGrupos(session);
			final ArrayList<Grupo> gruposselect = new ArrayList<Grupo>();
			
			try {
				add(new CheckBoxMultipleChoice("grupos", new Model(gruposselect),new ArrayList(daoGrupos.gruposde(user)),new ChoiceRenderer("nombre","idGrupo")));
				
			} catch (Exception e) {
				e.printStackTrace();
				setResponsePage(ErrorPage.ErrorRandom());
				
				add(new EmptyPanel("grupos"));
			}
			
			add(new Button("compartir"){
				@Override
				public void onSubmit() {
					super.onSubmit();
					
					for (Grupo grupo : gruposselect) {
						grupo.agregarReceta(receta);
						try {
							daoGrupos.saveOrUpdate(grupo);
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
					
				}
			});
		}
	}
}