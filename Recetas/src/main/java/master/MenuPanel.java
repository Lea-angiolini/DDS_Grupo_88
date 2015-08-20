package master;



import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.link.Link;

import Grupo88.BuscarReceta;
import Grupo88.PanelLinks;
import Grupo88.PanelLogin;
import Grupo88.PanelLogueado;

public class MenuPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SesionUsuario sesionActual = (SesionUsuario)getSession();
	public MenuPanel(String id) {
		super(id);
		
		
		if(sesionActual.estaLogueado()){
			add(new PanelLinks("linkPanel"));
			add(new PanelLogueado("loginPanel",sesionActual));
		}
		else
		{
			add(new EmptyPanel("linkPanel"));
			add(new PanelLogin("loginPanel"));
		}
	}
}
