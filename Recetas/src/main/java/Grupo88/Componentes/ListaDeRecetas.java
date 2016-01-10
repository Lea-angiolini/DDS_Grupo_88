package Grupo88.Componentes;

import java.util.ArrayList;

import master.MasterPage;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import Grupo88.Detalles.DetalleDeReceta;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class ListaDeRecetas extends Panel {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@SuppressWarnings("unchecked")
public ListaDeRecetas(String id, ArrayList<ObjetosDB.Receta> arrayList, final Usuario user, final MasterPage pagina) {
	// TODO Auto-generated constructor stub
	super(id);
	
	add(new DataView("tablaPopulares",  new ListDataProvider(arrayList)){

		private static final long serialVersionUID = 1L;

		@Override
		protected void populateItem(Item item) {
			// TODO Auto-generated method stub
			
			final Receta recetas= (Receta) item.getModelObject();
			
			final PageParameters pars = new PageParameters();
			pars.add("idReceta",recetas.getIdreceta());
			
			Link bton = new Link("bt"){

				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					recetas.consulta(user);
					final Page paginaActual = getPage();
					setResponsePage(new DetalleDeReceta(pars){
						@Override
						protected Page paginaRetorno() {
							return paginaActual;
						}
					});
				}
				
			};
			
			
			Usuario usercreador = recetas.getCreador();
			String creador = "";
			if(usercreador != null) creador = usercreador.getUsername();
			
			bton.addOrReplace(new Label("campo1",recetas.getNombre()));
			bton.addOrReplace(new Label("campo2",creador));
			bton.addOrReplace(new Label("campo3",recetas.getDificultad().getDificultad()));
			bton.addOrReplace(new Label("descripcion",recetas.getDetalle()));
			item.add(bton);
			
		}
	});
	
}

	
}
