package Grupo88;

import java.util.prefs.BackingStoreException;

import master.MasterPage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.eclipse.jetty.server.Server;

public class Login extends MasterPage {

//	private TextField<String> txtUsuario;
//	private PasswordTextField txtPassword;
//	
	public Login(){
		super();
		getMenuPanel().setVisible(false);
		add(new TextField<String>("txtUsername"));
		add(new PasswordTextField("txtPassword"));
		
	}
	
	public void onSubmit(){
		
		if(getString("username") == getString("password")){
			setResponsePage(this);
		}
			
		
	}
	

}