package Grupo88.Componentes;

import objetosWicket.SesionUsuario;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;





//import paquete AltaUsuario
import Grupo88.AltaUsuario.AltaUsuario;
import Grupo88.BuscarReceta.BuscarReceta;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Usuario;

public class PanelLogin extends Panel {

	private static final long serialVersionUID = -2565386407042130703L;
	SesionUsuario sesion = (SesionUsuario)getSession();
	
	public PanelLogin(String id) {
		super(id);
		add(new FrmLogin("FrmLogin"));
		
	}
		public class Login extends Fragment {

			private static final long serialVersionUID = 8239910741257831275L;

			public Login(String id, String markupId, MarkupContainer markupProvider) {
				super(id, markupId, markupProvider);
			
				FrmLogin frmLogin = new FrmLogin("FrmLogin");
				add(frmLogin);
			
				frmLogin.add(new Link<Object>("registrarse"){

					private static final long serialVersionUID = 2344641306055295057L;

				@Override
					public void onClick() {
						setResponsePage(AltaUsuario.class);
					}
				});
				
			}
		}
		
	public class FrmLogin extends Form<Object> {
		

		private static final long serialVersionUID = 4408451582416880736L;
		private Usuario usuario;
		private String error;
		private Label labelError;

		public FrmLogin(String id) {
			super(id);
			usuario = new Usuario();

			
			add(new TextField<String>("username",new PropertyModel<String>(usuario, "username")));
			add(new PasswordTextField("password",new PropertyModel<String>(usuario, "password")));
			add(labelError = new Label("lblError", Model.of("")));
			
			
			add(new Link<Object>("registrarse"){

				private static final long serialVersionUID = -3241707458938807369L;

				@Override
				public void onClick() {

					setResponsePage(AltaUsuario.class);
				}
			});
			
			add(new Link<Object>("buscarRecetas"){

				private static final long serialVersionUID = -1118345194034917710L;

				@Override
					public void onClick() {
						setResponsePage(BuscarReceta.class);
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
				error = "Usuario incorrecto";
				labelError.setDefaultModelObject(error);
			}
				
		}
		
	}
	
}