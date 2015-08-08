package master;

import objetosWicket.SesionUsuario;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.*;
import org.omg.CORBA.PRIVATE_MEMBER;

import ObjetosDB.Usuario;

public class MasterPage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	
	
	private Component headerPanel;
	private Component menuPanel;
	private Component footerPanel;
	//private Usuario user = ((SesionUsuario)getSession()).getUsuario().getObject();
	

	
	public MasterPage() {
		add(headerPanel = new HeaderPanel("header"));
		add(menuPanel = new MenuPanel("menu"));
		add(footerPanel = new FooterPanel("footer"));	

		
    }

	public Component getHeaderPanel() {
		return headerPanel;
	}


	public Component getMenuPanel() {
		return menuPanel;
	}


	public Component getFooterPanel() {
		return footerPanel;
	}
	
	
	
	
}
