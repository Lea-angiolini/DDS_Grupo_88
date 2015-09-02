package master;



import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;

import Grupo88.Componentes.PanelLinks;
import Grupo88.Componentes.PanelLogin;
import Grupo88.Componentes.PanelLogueado;

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
