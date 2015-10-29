package Grupo88.MisRecetas;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;

import Grupo88.AgregarReceta.AgregarReceta;
import Grupo88.Componentes.ListaDeRecetas;
import Grupo88.Inicio.Inicio;

public class MisRecetas extends RegisteredPage {

	private FrmMisRecetas frmMisRecetas;
	
	public MisRecetas(){
		super();
		
		add(frmMisRecetas = new FrmMisRecetas("frmMisRecetas"));
	
	}
	
	private class FrmMisRecetas extends Form{
		public FrmMisRecetas(String id) {
			super(id);			

			add(new ListaDeRecetas("listaMisRecetas", getUsuarioActual().cargarMisRecetas(getSessionBD()) , getUsuarioActual()));
			
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

