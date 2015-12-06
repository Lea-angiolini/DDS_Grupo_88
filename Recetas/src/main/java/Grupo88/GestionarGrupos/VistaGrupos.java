package Grupo88.GestionarGrupos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import Grupo88.Detalles.DetalleGrupo;
import ObjetosDB.Grupo;

public class VistaGrupos extends Panel implements Serializable{
	
	private static final long serialVersionUID = -3132622830685378321L;

	public VistaGrupos(String id, ArrayList<Grupo> grupos) {
		
		super(id);
		
		DataView<Grupo> dataView = new DataView<Grupo>("VistaGrupos", new ListDataProvider<Grupo>(grupos)) {

			private static final long serialVersionUID = 7897518560868712833L;

			@Override
			protected void populateItem(Item<Grupo> item) {
				
				Grupo grupo = item.getModelObject();
				final PageParameters pars = new PageParameters();
				pars.add("idGrupo",grupo.getIdGrupo());
				
				Link link = new Link("ver"){
					@Override
					public void onClick() {
						setResponsePage(DetalleGrupo.class,pars);
						
					}
				};
				
				link.add(new Label("nombre", grupo.getNombre()));
				link.add(new Label("creador", grupo.getCreador().getUsername()));
				link.add(new Label("descripcion", grupo.getDetalle()));
				item.add(link);
			}
		};
		
		dataView.setItemsPerPage(10);
		add(dataView);
		add(new PagingNavigator("navigator", dataView));
		
	}
	
}
