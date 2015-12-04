package Grupo88.Login;

import master.MasterPage;
import objetosWicket.ModelUsuario;
import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

//import paquete de AltaUsuario
import Grupo88.AltaUsuario.AltaUsuario;
import Grupo88.BuscarReceta.BuscarReceta;
import Grupo88.Inicio.Inicio;
//import ObjetosDB.ObjectodePrueba;
import ObjetosDB.Usuario;



public class Login extends MasterPage {

	private static final long serialVersionUID = 1L;
	//	private TextField<String> txtUsuario;
//	private PasswordTextField txtPassword;
//	
	Usuario user;
	
	private FrmLogin frmLogin;
	public Login(){
		super();
		
		//getMenuPanel().setVisible(false);
			
		add(frmLogin = new FrmLogin("FrmLogin"));
		
		frmLogin.add(new Link("registrarse"){
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
			
				//redirectToInterceptPage(new AltaUsuario());
				setResponsePage(AltaUsuario.class);
			}
		});
		
		
		frmLogin.add(new Link("buscarReceta"){
			
			public void onClick() {
				
				setResponsePage(BuscarReceta.class);
			}
		});
		
	}
	
	public class FrmLogin extends Form {
		
		
		//private String username;
		//private String password;
		private Usuario usuario;
		private String lblError;
		

		public FrmLogin(String id) {
			super(id);
			usuario = new Usuario();
			//setDefaultModel(new CompoundPropertyModel(this));

			//add(new TextField("username"));
			//add(new PasswordTextField("password"));
			add(new TextField<String>("username",new PropertyModel<String>(usuario, "username")));
			add(new PasswordTextField("password",new PropertyModel<String>(usuario, "password")));
			add(new Label("lblError"));
			//add(new Button("registrarse"));
			
		}
		
		
		/*public String getUsername(){
			return (String)username.getDefaultModelObject();
		}
		
		public String getPassword(){
			return (String)password.getDefaultModelObject();
		}*/
		
		public final void onSubmit() 
		{
			SesionUsuario sesion = (SesionUsuario)getSession();
			ModelUsuario mUsuario = new ModelUsuario(usuario);
			if(sesion.loguearUsuario(user) != null){
				sesion.setUsuario(user);
				setResponsePage(Inicio.class);
			}
			else {
				lblError = "Usuario incorrecto";
			}
		}
		
	}
		
}
