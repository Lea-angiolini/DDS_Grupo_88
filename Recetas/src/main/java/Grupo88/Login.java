package Grupo88;

import master.MasterPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;


public class Login extends MasterPage {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	private TextField<String> txtUsuario;
//	private PasswordTextField txtPassword;
//	
	private FrmLogin frmLogin;
	public Login(){
		super();
		getMenuPanel().setVisible(false);
		
		add(frmLogin = new FrmLogin("FrmLogin"));
		
		frmLogin.add(new Link("registrarse"){
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
			
				//redirectToInterceptPage(new AltaUsuario());
				setResponsePage(AltaUsuario.class);
			}
		});

		
	}
	
	public class FrmLogin extends Form {
		
		
		private String username;
		private String password;
		private String lblError;
		

		public FrmLogin(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));

			add(new TextField("username"));
			add(new PasswordTextField("password"));
			add(new Label("lblError"));
			//add(new Button("registrarse"));
			
		}
		
		
		/*public String getUsername(){
			return (String)username.getDefaultModelObject();
		}
		
		public String getPassword(){
			return (String)password.getDefaultModelObject();
		}*/
		
		public final void onSubmit(){
			
			if(!password.equals("pass") && !username.equals("usuario"))
				lblError = ("Usuario incorrecto");
				
				return;
			
		}
		
		
		
	}
}