package Grupo88.GestionarRecetas;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;

import Grupo88.AgregarReceta.AgregarReceta;
import Grupo88.Inicio.Inicio;

public class GestionarRecetas extends RegisteredPage {	
	
	private FrmGestionarRecetas frmGestionarRecetas;
	
	public GestionarRecetas(){
		super();
		
		add(frmGestionarRecetas = new FrmGestionarRecetas("FrmGestionarRecetas"));
		
		frmGestionarRecetas.add(new Link("agregarReceta"){
			
			public void onClick(){
			setResponsePage(AgregarReceta.class);
			}
		});
		
		frmGestionarRecetas.add(new Link("volver"){
			
			public void onClick(){
			setResponsePage(Inicio.class);
			}
		});
		
	}
	
	public class FrmGestionarRecetas extends Form {
		
		
		public FrmGestionarRecetas(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));

			
		}
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		}
	}
}