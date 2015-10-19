package objetosWicket;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import Database.Browser;
import Database.DAOUsuarios;
import ObjetosDB.Usuario;

public class SesionUsuario extends WebSession {
	
	ModelUsuario usuario;
	DAOUsuarios daousuario;
	
	public SesionUsuario(Request request) {
		super(request);
		ModelUsuario invitado = new ModelUsuario(new Usuario());
		invitado.getObject().setUsername("Invitado");
		this.usuario = invitado;
		setAttribute("usuario", invitado);
		daousuario = new DAOUsuarios();
	}
	
	public static SesionUsuario get(){
		return (SesionUsuario)Session.get();
	}
	
	public boolean loguearUsuario(ModelUsuario user){
		if (usuario.getObject().getUsername() == "Invitado"){
			Usuario logueado = daousuario.loguear(user.getObject().getUsername(),  user.getObject().getPassword());
			if (logueado != null){
				user.setObject(logueado);
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
