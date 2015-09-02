package Grupo88.Componentes;

import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import Grupo88.Inicio.Inicio;

public class PanelLogueado extends Panel {

	private SesionUsuario sesionActual;
	public PanelLogueado(String id, SesionUsuario sesionActual) {
		super(id);
		this.sesionActual = sesionActual;
		add(new FrmLogueado("FrmLogueado"));
		
	}
	
	public class FrmLogueado extends Form {

		public FrmLogueado(String id) {
			super(id);
			
			add(new Label("mensaje","Bienvenido/a "+ sesionActual.getUsuarioActual().getNombre()));
			
			add(new Link("cerrarSesion"){
				@Override
				public void onClick() {
					// TODO Auto-generated method stub
					sesionActual.desloguearUsuario();
					setResponsePage(Inicio.class);
				}
			});
		}
	}
	
	
		
}