package Grupo88;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import ObjetosDB.Recetas;
import ObjetosDB.Recetas.Receta;
import ObjetosDB.Usuario;

public class ListaDeRecetas extends Panel {
public ListaDeRecetas(String id, ArrayList<Receta> recetas, final Usuario user) {
	// TODO Auto-generated constructor stub
	super(id);
	
	add(new DataView("tablaPopulares",  new ListDataProvider(recetas)){
		@Override
		protected void populateItem(Item item) {
			// TODO Auto-generated method stub
			
			final Receta recetas= (Receta) item.getModelObject();
			
			final PageParameters pars = new PageParameters();
			pars.add("idReceta",recetas.getIdreceta());
			
			Link bton = new Link("bt"){
				@Override
				public void onClick() {
					// TODO Auto-generated method stub
					recetas.consulta(user);
					setResponsePage(DetalleDeReceta.class,pars);
					
				}
				
			};
			
			
			//item.addOrReplace(new Label("indice",item.getIndex()));
			
			bton.addOrReplace(new Label("campo1",recetas.getNombre()));
			bton.addOrReplace(new Label("campo2",recetas.getCreador()));
			bton.addOrReplace(new Label("campo3",recetas.getDificultad().getDificultad()));
			bton.addOrReplace(new Label("descripcion","Arreglar recetas hdp"));
			item.add(bton);
			
		}
	});
	
}

	
}
