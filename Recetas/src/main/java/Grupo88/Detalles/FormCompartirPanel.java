package Grupo88.Detalles;

import java.util.ArrayList;

import master.ErrorPage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.hibernate.Session;

import ObjetosDB.Grupo;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class FormCompartirPanel extends Panel{
	
	private NegocioRecetas negocio;
	private Session session;
	private Usuario user;
	private Receta receta;
	
	public FormCompartirPanel(String id, Session sess, Usuario use, Receta rec, NegocioRecetas negocio) {
		super(id);
		
		this.session = sess;
		this.user = use;
		this.receta = rec;
		this.negocio = negocio;
		
		add(new FormCompartir(id, sess, use, rec));
	}
	private class FormCompartir extends Form{
		

		
		public FormCompartir(String id, Session sess, Usuario use, Receta rec) {
			super(id);
			
			final ArrayList<Grupo> gruposselect = new ArrayList<Grupo>();
			ArrayList<Grupo> todosGrupos;
			
			try {
				todosGrupos = new ArrayList<Grupo>(negocio.gruposde(user));	
				add(new CheckBoxMultipleChoice("grupos", new Model(gruposselect),todosGrupos,new ChoiceRenderer("nombre","idGrupo")));
			} catch (Exception e) {
				e.printStackTrace();
				setResponsePage(ErrorPage.ErrorRandom());
				add(new EmptyPanel("grupos"));
			}

			add(new Button("compartir"){
				@Override
				public void onSubmit() {
					super.onSubmit();
					
					negocio.compartirEnGrupo(gruposselect,receta);
					
				}
			});
		}
	}
}