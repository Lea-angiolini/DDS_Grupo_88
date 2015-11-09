package Grupo88.GestionarPerfil;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.Model;

import ObjetosDB.Usuario;

public class CambioPass extends RegisteredPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private FrmCambioPass frmCambioPass;
	private Usuario usuario = getUsuarioActual();
	public CambioPass() {
		super();
		
		add(frmCambioPass = new FrmCambioPass("frmCambioPass"));
	}
	
	private class FrmCambioPass extends Form<Object>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Model<String> mPassAnt = new Model<String>(); 
		private Model<String> mPassNva = new Model<String>();
		
		@SuppressWarnings("unused")
		public FrmCambioPass(String id) {
			super(id);
			
			PasswordTextField passAnt = new PasswordTextField("passAnt", mPassAnt); 
			PasswordTextField passNva = new PasswordTextField("passNva", mPassNva);
			PasswordTextField passNvaRep = new PasswordTextField("passNvaRep", Model.of(""));
			
			add(passNva);
			add(passNvaRep);
			add(new EqualPasswordInputValidator(passNva, passNvaRep));
			
			
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			if(usuario.getPassword().equals(mPassAnt.getObject())){
				// llamada a la BD
			}
			
		}
	}
	
	
}
