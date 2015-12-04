package Grupo88.GestionarRecetas;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;

import Grupo88.AgregarReceta.AgregarReceta;
import Grupo88.Inicio.Inicio;

public class GestionarRecetas extends RegisteredPage {	
	
	private static final long serialVersionUID = 1L;
	private FrmGestionarRecetas frmGestionarRecetas;
	
	public GestionarRecetas(){
		super();
		
		add(frmGestionarRecetas = new FrmGestionarRecetas("FrmGestionarRecetas"));
		
		frmGestionarRecetas.add(new Link<Object>("agregarReceta"){
			
			private static final long serialVersionUID = 1L;

			public void onClick(){
			setResponsePage(AgregarReceta.class);
			}
		});
		
		frmGestionarRecetas.add(new Link<Object>("volver"){
			
			private static final long serialVersionUID = 1L;

			public void onClick(){
			setResponsePage(Inicio.class);
			}
		});
		
	}
	
	public class FrmGestionarRecetas extends Form<Object> {

		private static final long serialVersionUID = 1L;

		public FrmGestionarRecetas(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel<FrmGestionarRecetas>(this));

			
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
		}
	}
}