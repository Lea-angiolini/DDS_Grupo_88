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
	
	public MisRecetas(){
		super();
		
		add(frmMisRecetas = new FrmMisRecetas("frmMisRecetas"));
	
	}
	
	private class FrmMisRecetas extends Form{
		public FrmMisRecetas(String id) {
			super(id);			

			add(new ListaDeRecetas("listaMisRecetas", getUsuarioActual().cargarMisRecetas().ObtenerColeccionRecetas() , getUsuarioActual()));
			
			add(new Link("cancelar"){
				public void onClick(){
					setResponsePage(Inicio.class);
				}
			});
			
			add(new Link("nvaReceta"){
				public void onClick(){
				setResponsePage(AgregarReceta.class);
				}
			});	
	}
}
}

