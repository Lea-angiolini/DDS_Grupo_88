package objetosWicket;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;
import org.hibernate.FlushMode;

import Database.DAOUsuarios;
import Database.HibernateUtil;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Usuario;
import ObjetosDB.UsuarioNoRegistrado;

public class SesionUsuario extends WebSession {
	
	private static final long serialVersionUID = -8148906114977069921L;
	private Usuario usuario;
	private DAOUsuarios daousuario;
	private org.hibernate.Session session;
	
	public SesionUsuario(Request request) {
		super(request);
		Usuario invitado = new UsuarioNoRegistrado();
		this.usuario = invitado;
		abrirSessionDB();
	}
	
	private void abrirSessionDB(){
		session = HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.COMMIT);
		daousuario = new DAOUsuarios(session);
	}
	public static SesionUsuario get(){
		return (SesionUsuario)Session.get();
	}
	
	public Usuario loguearUsuario(Usuario user){
		if (usuario instanceof UsuarioNoRegistrado){
			Usuario logueado;
			try {
				logueado = daousuario.loguear(user.getUsername(), user.getPassword());
				if (logueado != null){
					return logueado;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		

		return null; 
	}

	public void reiniciarSesion(){
		session.close();
		abrirSessionDB();
		if(!(usuario instanceof UsuarioNoRegistrado)){
			try {
				usuario = daousuario.get(usuario.getUsername());
			} catch (Exception e) {
				usuario = new UsuarioNoRegistrado();
			}
		}
		RequestCycle.get().setResponsePage(Inicio.class);
	}
	public void desloguearUsuario(){
		session.close();
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
		return !(getUsuario() instanceof UsuarioNoRegistrado);
	}

	public org.hibernate.Session getSessionDB() {
		return session;
	}

	public void setSession(org.hibernate.Session session) {
		this.session = session;
	}
}
