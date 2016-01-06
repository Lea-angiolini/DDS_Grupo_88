package master;

import objetosWicket.SesionUsuario;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.*;
import org.apache.wicket.request.http.WebResponse;
import org.hibernate.Session;
import org.omg.CORBA.PRIVATE_MEMBER;

import ObjetosDB.Usuario;

public class MasterPage extends WebPage {
	private static final long serialVersionUID = 1L;

	private Component headerPanel;
	private Component menuPanel;
	private Component footerPanel;
	private Usuario user = ((SesionUsuario)getSession()).getUsuarioActual();
	private SesionUsuario sesion = (SesionUsuario)getSession();
	
	public Usuario getUsuarioActual(){
		return user;
	}
	
	public Session getSessionBD(){
		return sesion.getSessionDB();
	}
	
	public SesionUsuario getSessionUser(){
		return sesion;
	}
	
	public MasterPage() {
		add(headerPanel = new HeaderPanel("header"));
		add(menuPanel = new MenuPanel("menu"));
		add(footerPanel = new FooterPanel("footer"));	
		headerPanel.setVisible(false);
		/*if(user.getUsername() != "Invitado")
			sessionDB.refresh(user);*/
		
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
	
	
	@Override
	protected void configureResponse(WebResponse response) {
		// TODO Auto-generated method stub
		super.configureResponse(response);
		response.setHeader("Cache-Control", 
	             "no-cache, max-age=0,must-revalidate, no-store");
		response.setHeader("Expires","-1");
		response.setHeader("Pragma","no-cache");
	}
	
}
