package Grupo88;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import master.MasterPage;
import master.RegisteredPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import ObjetosDB.Recetas;
import ObjetosDB.Recetas.Receta;
import ObjetosDB.Usuario;

public class MisRecetas extends RegisteredPage {

	private FrmMisRecetas frmMisRecetas;
	private Usuario user = ((SesionUsuario)getSession()).getUsuario().getObject();
	
	public MisRecetas(){
		super();
		getMenuPanel().setVisible(false);
		
		add(frmMisRecetas = new FrmMisRecetas("frmMisRecetas"));
		
		frmMisRecetas.add(new Link("cancelar"){
			public void onClick(){
				setResponsePage(Inicio.class);
			}
		});
		
		frmMisRecetas.add(new Link("nvaReceta"){
			public void onClick(){
			setResponsePage(AgregarReceta.class);
			}
		});	
	}
	
	public class FrmMisRecetas extends Form{
		public FrmMisRecetas(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));
			
			Recetas recetas;
			
			add(new ListaDeRecetas("listaMisRecetas", getUsuarioActual().cargarMisRecetas().ObtenerColeccionRecetas() , user));
	}
}
	
	private DataView generarTabla(){
		
		return (new DataView("tablaPopulares",  new ListDataProvider(user.cargarMisRecetas().ObtenerColeccionRecetas())){
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
						setResponsePage(DetalleDeReceta.class,pars);
					}
					
				};
				
				
				//item.addOrReplace(new Label("indice",item.getIndex()));
				 
				bton.addOrReplace(new Label("campo1",recetas.getNombre()));
				bton.addOrReplace(new Label("campo2",recetas.getCreador()));
				bton.addOrReplace(new Label("campo3",recetas.getDificultad().getDificultad()));
				
				item.add(bton);
				
			}
		});
	}
}

