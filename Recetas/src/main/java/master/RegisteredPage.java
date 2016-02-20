package master;

import ObjetosDB.UsuarioNoRegistrado;

public class RegisteredPage extends MasterPage {
	private static final long serialVersionUID = 1L;
	
	public RegisteredPage() {
		
		super();
		
		if (getUsuarioActual() instanceof UsuarioNoRegistrado){
			setResponsePage(new ErrorPage("Debe estar registrado"));
		}
	
	}
	
	
}

	