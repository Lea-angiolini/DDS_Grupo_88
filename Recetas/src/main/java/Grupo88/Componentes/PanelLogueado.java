package Grupo88.Componentes;

import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import Grupo88.Inicio.Inicio;

public class PanelLogueado extends Panel {


	private static final long serialVersionUID = -8851794206154327772L;
	private SesionUsuario sesionActual;
	public PanelLogueado(String id, SesionUsuario sesionActual) {
		super(id);
		this.sesionActual = sesionActual;
		add(new FrmLogueado("FrmLogueado"));
		
	}
	
	public class FrmLogueado extends Form<Object> {

		private static final long serialVersionUID = -5299385406536454870L;

		public FrmLogueado(String id) {
			super(id);
			
			add(new Label("mensaje", sesionActual.getUsuarioActual().getNombre()));
			add(new PanelLinks("panelLinks"));
			add(new Link<Object>("cerrarSesion"){

				private static final long serialVersionUID = 8827909601902171076L;

				@Override
				public void onClick() {
					sesionActual.desloguearUsuario();
					setResponsePage(Inicio.class);
				}
			});
		}
	}
	
	
		
}