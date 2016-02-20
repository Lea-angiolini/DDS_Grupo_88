package Grupo88.Reportes;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import Grupo88.Detalles.DetalleDeReceta;
import ObjetosDB.Historial;

public class PanelListaRecetas extends Panel{
	
	private static final long serialVersionUID = -2078421217794424583L;

	public PanelListaRecetas(String id, List<Historial> lista) {
		super(id);
		
		DataView<Historial> dataView = new DataView<Historial>("recetas", new ListDataProvider<Historial>(lista)) {
		
			private static final long serialVersionUID = -290147668025817794L;

			@Override
			protected void populateItem(Item<Historial> item) {
				Historial historial = (Historial) item.getModelObject();
				
				final PageParameters pars = new PageParameters();
				pars.add("idReceta", historial.getReceta().getIdreceta());
				
				Link<Object> link = new Link<Object>("irReceta") {

					private static final long serialVersionUID = -4045024294339586828L;

					@Override
					public void onClick() {
						setResponsePage(DetalleDeReceta.class,pars);
					}
				};
				link.add(new Label("nombre", historial.getReceta().getNombre()));
				link.add(new Label("fecha", historial.getFecha()));
				item.add(link);
			}
		};
		
		add(dataView);
	}
	
	
}
