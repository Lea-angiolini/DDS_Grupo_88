package master;

import Grupo88.Inicio.Inicio;

public class RegisteredPage extends MasterPage {
	private static final long serialVersionUID = 1L;
	
	public RegisteredPage() {
		
		super();
		
		if (getUsuarioActual().getUsername() == "Invitado"){
			setResponsePage(Inicio.class);
		}
		
			
	
	}
	
	
}

	