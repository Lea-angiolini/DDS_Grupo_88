package master;

import objetosWicket.SesionUsuario;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.*;
import org.omg.CORBA.PRIVATE_MEMBER;

import ObjetosDB.Usuario;

public class ErrorPage extends MasterPage {
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public ErrorPage(String msg) {
		super();
		this.msg = msg;
		add(new FormError("frmERROR"));
		
    }

	private class FormError extends Form{
		public FormError(String id) {
			// TODO Auto-generated constructor stub
			super(id);
		add(new Label("msgERROR", msg));
		}
	}
}
