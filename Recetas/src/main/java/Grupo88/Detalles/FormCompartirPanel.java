package Grupo88.Detalles;

import java.util.ArrayList;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import ObjetosDB.Grupo;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class FormCompartirPanel extends Panel{
	
	private static final long serialVersionUID = 2728041827516076546L;
	private NegocioRecetas negocio;
	private Usuario user;
	private Receta receta;
	
	public FormCompartirPanel(String id, Usuario use, Receta rec, NegocioRecetas negocio) {
		super(id);
		
		this.user = use;
		this.receta = rec;
		this.negocio = negocio;
		
		add(new FormCompartir(id, use, rec));
	}
	private class FormCompartir extends Form<Object>{

		private static final long serialVersionUID = 1606432931573272105L;

		public FormCompartir(String id, Usuario use, Receta rec) {
			super(id);
			
			final ArrayList<Grupo> gruposselect = new ArrayList<Grupo>();
			ArrayList<Grupo> todosGrupos = (ArrayList<Grupo>) negocio.gruposde(user);
			
			if(!todosGrupos.isEmpty())
				add(new CheckBoxMultipleChoice<Grupo>("grupos", new Model<ArrayList<Grupo>>(gruposselect),todosGrupos,new ChoiceRenderer<Grupo>("nombre","idGrupo")));
			else
				add(new EmptyPanel("grupos"));
			
			add(new Button("compartir"){

				private static final long serialVersionUID = 8999520202589069677L;

				@Override
				public void onSubmit() {
					super.onSubmit();
					
					negocio.compartirEnGrupo(gruposselect,receta);
					
				}
			});
		}
	}
}