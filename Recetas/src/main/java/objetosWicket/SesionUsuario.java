package objetosWicket;

import javax.swing.JOptionPane;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import Database.Browser;
import Database.DAOUsuarios;
import ObjetosDB.Usuario;

public class SesionUsuario extends WebSession {
	
	private Usuario usuario;
	private DAOUsuarios daousuario;
	
	public SesionUsuario(Request request) {
		super(request);
		Usuario invitado = new Usuario();
		invitado.setUsername("Invitado");
		this.usuario = invitado;
		setAttribute("usuario", invitado);
		daousuario = new DAOUsuarios();
	}
	
	public static SesionUsuario get(){
		return (SesionUsuario)Session.get();
	}
	
	public Usuario loguearUsuario(Usuario user){
		if (usuario.getUsername() == "Invitado"){
			Usuario logueado = daousuario.loguear(user.getUsername(), user.getPassword());
			if (logueado != null){
				return logueado;
				
			}
		}
		return null;
	}

	
	public void desloguearUsuario(){
		this.invalidateNow();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Usuario getUsuarioActual(){
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		setAttribute("usuario", usuario);
		this.usuario= usuario; 
	}
	
	public boolean estaLogueado(){
		return getUsuario().getUsername() != "Invitado";
	}
}
