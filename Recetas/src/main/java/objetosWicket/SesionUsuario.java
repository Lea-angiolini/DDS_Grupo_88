package objetosWicket;

import java.io.ObjectInputStream.GetField;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.authorization.*;

import Database.Browser;
import ObjetosDB.Usuario;

public class SesionUsuario extends WebSession {
	
	Usuario usuario;
	
	public SesionUsuario(Request request) {
		super(request);
	}
	
	public static SesionUsuario get(){
		return (SesionUsuario)Session.get();
	}
	
	public boolean loguearUsuario(ModelUsuario user){
		if (usuario == null){
			if (Browser.Login(user.getObject().getUsername(), user.getObject().getPassword())){
				this.usuario = user.getObject();
			}
		}
		return usuario != null;
	}


	public ModelUsuario getUsuario() {
		return (ModelUsuario)getAttribute("usuario");
	}


	public void setUsuario(ModelUsuario usuario) {
		setAttribute("usuario", usuario);
	}
	
	public boolean estaLogueado(){
		return getUsuario() != null;
	}
}
