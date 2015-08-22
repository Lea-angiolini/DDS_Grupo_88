package Grupo88;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import ObjetosDB.Usuario;

public class CambioPass extends RegisteredPage {

	private FrmCambioPass frmCambioPass;
	private Usuario usuario = getUsuarioActual();
	public CambioPass() {
		// TODO Auto-generated constructor stub
		super();
		
		add(frmCambioPass = new FrmCambioPass("frmCambioPass"));
	}
	
	private class FrmCambioPass extends Form{
		
		private Model<String> mPassAnt = new Model<String>(); 
		private Model<String> mPassNva = new Model<String>();
		
		public FrmCambioPass(String id) {
			super(id);
			// TODO Auto-generated constructor stub
			
			PasswordTextField passAnt = new PasswordTextField("passAnt", mPassAnt); 
			PasswordTextField passNva = new PasswordTextField("passNva", mPassNva);
			PasswordTextField passNvaRep = new PasswordTextField("passNvaRep", Model.of(""));
			
			add(passNva);
			add(passNvaRep);
			add(new EqualPasswordInputValidator(passNva, passNvaRep));
			
			
		}
		
		@Override
		protected void onSubmit() {
			// TODO Auto-generated method stub
			super.onSubmit();
			
			if(usuario.getPassword().equals(mPassAnt.getObject())){
				// llamada a la BD
			}
			
		}
	}
	
	
}
