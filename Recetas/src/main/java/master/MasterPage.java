package master;

import objetosWicket.SesionUsuario;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.*;
import org.hibernate.Session;
import org.omg.CORBA.PRIVATE_MEMBER;

import ObjetosDB.Usuario;

public class MasterPage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	
	
	private Component headerPanel;
	private Component menuPanel;
	private Component footerPanel;
	private Usuario user = ((SesionUsuario)getSession()).getUsuarioActual();
	private Session session = ((SesionUsuario)getSession()).getSession();
	
	public Usuario getUsuarioActual(){
		return user;
	}
	
	public Session getSessionBD(){
		return session;
	}
	
	public MasterPage() {
		add(headerPanel = new HeaderPanel("header"));
		add(menuPanel = new MenuPanel("menu"));
		add(footerPanel = new FooterPanel("footer"));	
		headerPanel.setVisible(false);
		if(user.getUsername() != "Invitado")
			session.refresh(user);
		
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
