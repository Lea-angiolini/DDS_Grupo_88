package Grupo88.Reportes;

import java.util.ArrayList;

import master.RegisteredPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import Grupo88.Detalles.DetalleDeReceta;
import ObjetosDB.Receta;

public class ReporteNuevasRecetas extends RegisteredPage {

	private static final long serialVersionUID = -4968503463362404667L;
	@SuppressWarnings("unused")
	private FormNuevasRecetas formNuevasRecetas;
	private NegocioReportes negocio;
	
	public ReporteNuevasRecetas() {
		negocio = new NegocioReportes(getSessionUser());
		
		add(formNuevasRecetas = new FormNuevasRecetas("formNuevasRecetas"));
	}
	
	private class FormNuevasRecetas extends Form<Object>{
		
		private static final long serialVersionUID = 2680549295180106021L;

		public FormNuevasRecetas(String id) {
			super(id);
			
			ArrayList<Receta> listaRecetas = negocio.recetasCreadas();	
			
			DataView<Receta> dataView = new DataView<Receta>("recetasCreadas", new ListDataProvider<Receta>(listaRecetas)) {

				private static final long serialVersionUID = -7966593687331994161L;

				@Override
				protected void populateItem(Item<Receta> item) {
					Receta receta = item.getModelObject();
					final PageParameters pars = new PageParameters();
					pars.add("idReceta", receta.getIdreceta());
					
					Link<Object> link = new Link<Object>("receta"){

						private static final long serialVersionUID = 4101263198700922654L;

						@Override
						public void onClick() {
							setResponsePage(DetalleDeReceta.class,pars);
						}
					};
					
					link.add(new Label("nombre",receta.getNombre()));
					item.add(link);
				}
			};
			
			add(dataView);
		}
	}
}
