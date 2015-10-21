package Grupo88.Componentes;

import objetosWicket.ModelUsuario;
import objetosWicket.SesionUsuario;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

//import paquete AltaUsuario
import Grupo88.AltaUsuario.AltaUsuario;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Usuario;

public class PanelLogin extends Panel {

	ModelUsuario mUsuario;
	SesionUsuario sesion = (SesionUsuario)getSession();
	
	public PanelLogin(String id) {
		super(id);
		add(new FrmLogin("FrmLogin"));
		
	}

		
		
		
		public class Login extends Fragment {

			public Login(String id, String markupId, MarkupContainer markupProvider) {
				super(id, markupId, markupProvider);
				// TODO Auto-generated constructor stub
			
				FrmLogin frmLogin = new FrmLogin("FrmLogin");
				add(frmLogin);
			
				frmLogin.add(new Link("registrarse"){
				@Override
					public void onClick() {
						setResponsePage(AltaUsuario.class);
					}
				});
				
			}
		}
	
	public class FrmLogin extends Form {
		
		
	
		private Usuario usuario;
		private String lblError;
		

		public FrmLogin(String id) {
			super(id);
			usuario = new Usuario();

			
			add(new TextField<String>("username",new PropertyModel<String>(usuario, "username")));
			add(new PasswordTextField("password",new PropertyModel<String>(usuario, "password")));
			add(new Label("lblError"));
			
			
			add(new Link("registrarse"){
				@Override
				public void onClick() {
					// TODO Auto-generated method stub
				
					//redirectToInterceptPage(new AltaUsuario());
					setResponsePage(AltaUsuario.class);
				}
			});
		
			
		}
		
		
		public final void onSubmit() 
		{
			Usuario logueado = sesion.loguearUsuario(usuario);
			if(logueado != null){
				sesion.setUsuario(logueado);
				setResponsePage(Inicio.class);
			}
			else {
				lblError = "Usuario incorrecto";
			}
				
		}
		
	}
	
	
	public class Logout extends Fragment {

		public Logout(String id, String markupId, MarkupContainer markupProvider) {
			super(id, markupId, markupProvider, mUsuario);
			
			
			FrmLogout frmLogout = new FrmLogout("frmLogout");
			add(frmLogout);
			
		}
		
	}
	
	
	public class FrmLogout extends Form{
		 
		public FrmLogout(String id){
			super(id);
			
			
			
		}
	}
	
	
	
		
}