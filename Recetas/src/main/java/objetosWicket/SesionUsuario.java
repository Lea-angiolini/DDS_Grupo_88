package objetosWicket;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import Database.Browser;
import ObjetosDB.Usuario;

public class SesionUsuario extends WebSession {
	
	ModelUsuario usuario;
	
	
	public SesionUsuario(Request request) {
		super(request);
		ModelUsuario invitado = new ModelUsuario(new Usuario());
		invitado.getObject().setUsername("Invitado");
		this.usuario = invitado;
		setAttribute("usuario", invitado);
	}
	
	public static SesionUsuario get(){
		return (SesionUsuario)Session.get();
	}
	
	public boolean loguearUsuario(ModelUsuario user){
		if (usuario.getObject().getUsername() == "Invitado"){
			if (Browser.Login(user.getObject().getUsername(), user.getObject().getPassword())){
				this.usuario = user;
				return true;
			}
		}
		return false;
	}

	
	public void desloguearUsuario(){
		this.invalidateNow();
	}
	
	public ModelUsuario getUsuario() {
		return (ModelUsuario)getAttribute("usuario");
	}
	
	public Usuario getUsuarioActual(){
		return getUsuario().getObject();
	}

	public void setUsuario(ModelUsuario usuario) {
		setAttribute("usuario", usuario);
	}
	
	public boolean estaLogueado(){
		return getUsuario().getObject().getUsername() != "Invitado";
	}
}
