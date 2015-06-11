package Grupo88;

import java.util.prefs.BackingStoreException;

import master.MasterPage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.jetty.server.Server;
import org.apache.wicket.Session;

public class AltaUsuario extends MasterPage {

//	private TextField<String> txtUsuario;
//	private PasswordTextField txtPassword;
//	
	
	public AltaUsuario(){
		super();
		getMenuPanel().setVisible(false);
		

		add(new FrmAltaUsuario("FrmAltaUsuario"));
		
		add(new Link("cancelar"){
			
			@Override
			public void onClick() {
			
				setResponsePage(Login.class);
				
			}
		});
	}
	
	public class FrmAltaUsuario extends Form {
		
		
		private String username;
		private String password;
		private String repPassword;
		private String email;
		

		public FrmAltaUsuario(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));

			add(new TextField("username"));
			add(new PasswordTextField("password"));
			add(new PasswordTextField("repPassword"));
			add(new EmailTextField("email"));
			//add(new Button("registrarse"));
			
		}
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		}
	}
}