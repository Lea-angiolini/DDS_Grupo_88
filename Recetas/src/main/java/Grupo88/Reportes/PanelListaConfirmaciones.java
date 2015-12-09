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
import ObjetosDB.Confirmacion;

public class PanelListaConfirmaciones extends Panel{
	
	private static final long serialVersionUID = -2078421217794424583L;

	public PanelListaConfirmaciones(String id, List<Confirmacion> lista) {
		super(id);
		
		DataView<Confirmacion> dataView = new DataView<Confirmacion>("recetas", new ListDataProvider<Confirmacion>(lista)) {
		
			private static final long serialVersionUID = -290147668025817794L;

			@Override
			protected void populateItem(Item<Confirmacion> item) {
				Confirmacion historial = (Confirmacion) item.getModelObject();
				
				final PageParameters pars = new PageParameters();
				pars.add("idReceta", historial.getReceta().getIdreceta());
				
				Link link = new Link("irReceta") {
					@Override
					public void onClick() {
						setResponsePage(DetalleDeReceta.class,pars);
					}
				};
				link.add(new Label("nombre", historial.getReceta().getNombre()));
				link.add(new Label("calorias", historial.getReceta().getCalorias()));
				item.add(link);
			}
		};
		
		add(dataView);
	}
	
	
}
