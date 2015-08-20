package Grupo88;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;

import objetosWicket.ModelUsuario;
import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.junit.runner.Computer;

import Grupo88.Login.FrmLogin;
import ObjetosDB.Usuario;

public class PanelLogueado extends Panel {

	private SesionUsuario sesionActual;
	public PanelLogueado(String id, SesionUsuario sesionActual) {
		super(id);
		this.sesionActual = sesionActual;
		add(new FrmLogueado("FrmLogueado"));
		
	}
	
	public class FrmLogueado extends Form {

		public FrmLogueado(String id) {
			super(id);
			
			add(new Label("mensaje","Bienvenido/a "+ sesionActual.getUsuarioActual().getNombre()));
			
			add(new Link("cerrarSesion"){
				@Override
				public void onClick() {
					// TODO Auto-generated method stub
					sesionActual.desloguearUsuario();
					setResponsePage(Inicio.class);
				}
			});
		}
	}
	
	
		
}