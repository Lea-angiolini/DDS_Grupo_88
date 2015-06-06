package Grupo88;

import java.util.prefs.BackingStoreException;

import master.MasterPage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.jetty.server.Server;

public class Login extends MasterPage {

//	private TextField<String> txtUsuario;
//	private PasswordTextField txtPassword;
//	
	private FrmLogin frmLogin;
	public Login(){
		super();
		getMenuPanel().setVisible(false);
		
		add(frmLogin = new FrmLogin("FrmLogin"));
		
	}
	
	public class FrmLogin extends Form {
		
		
		private String username;
		private String password;
		private String nombreDeMierda;

		public FrmLogin(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));

			add(new TextField("username"));
			add(new PasswordTextField("password"));
			add(new Label("nombreDeMierda"));
		}
		/*
		public String getUsername(){
			return (String)username.getDefaultModelObject();
		}
		
		public String getPassword(){
			return (String)password.getDefaultModelObject();
		}
		*/
		public final void onSubmit(){
			
			if(password != "pass" && username != "usuario")
				//lblError.setDefaultModelObject("Usuario incorrecto");
				
				return;
			
		}
	}
}