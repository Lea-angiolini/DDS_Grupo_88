package Grupo88.GestionarPerfil;

import master.ErrorPage;
import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.hibernate.exception.ConstraintViolationException;

import ObjetosDB.Usuario;

public class CambioPass extends RegisteredPage {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private FrmCambioPass frmCambioPass;
	private Usuario usuario = getUsuarioActual();
	public CambioPass() {
		super();
		
		add(frmCambioPass = new FrmCambioPass("frmCambioPass"));
	}
	
	private class FrmCambioPass extends Form<Object>{
		
		private static final long serialVersionUID = 1L;
		private Model<String> mPassAnt = new Model<String>(); 
		private Model<String> mPassNva = new Model<String>();
		private NegocioGestionarPerfil negocio;
		private PasswordTextField passAnt;
		private PasswordTextField passNva;
		private PasswordTextField passNvaRep;
		private Usuario user;
		
		@SuppressWarnings("unused")
		public FrmCambioPass(String id) {
			super(id);
			
			user = getUsuarioActual();
			
			negocio = new NegocioGestionarPerfil(getSessionUser());
			
			passAnt = new PasswordTextField("passAnt", mPassAnt); 
			passNva = new PasswordTextField("passNva", mPassNva);
			passNvaRep = new PasswordTextField("passNvaRep", Model.of(""));
			
			add(passAnt);
			add(passNva);
			add(passNvaRep);
			
			add(new FeedbackPanel("feedback"));
			
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			if(!usuario.getPassword().equals(mPassAnt.getObject())){
				info("La contraseña es incorrecta");
				return;
			}
			
			if(!passNva.getModelObject().equals(passNvaRep.getModelObject())){
				info("Las contraseñas nuevas deben coincidir");
				return;
			}
			
			user.setPassword(passNva.getModelObject());
			
			if(negocio.guardarUsuario(user))
				setResponsePage(new ErrorPage("Su contraseña ha sido actualizada"));
			else {
				info(negocio.getError());
			}
		}
	}
	
	
}
