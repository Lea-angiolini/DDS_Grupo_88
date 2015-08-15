package master;

import objetosWicket.SesionUsuario;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.*;
import org.omg.CORBA.PRIVATE_MEMBER;

import Grupo88.Login;
import ObjetosDB.Usuario;

public class RegisteredPage extends MasterPage {
	private static final long serialVersionUID = 1L;
	
	public RegisteredPage() {
		super();
		if (getUsuarioActual().getUsername() == "Invitado"){
			setResponsePage(Login.class);
			
		}
	}
	
	
}

	