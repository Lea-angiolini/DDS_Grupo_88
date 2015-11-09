package objetosWicket;

import org.apache.wicket.model.LoadableDetachableModel;

import ObjetosDB.Usuario;

public class ModelUsuario extends LoadableDetachableModel<Usuario> {

	private String username;
	private String password;
	
	
	public ModelUsuario(Usuario user) {
		// TODO Auto-generated constructor stub
		this.username = user.getUsername();
		this.password = user.getPassword();
	}
	
	@Override
	protected Usuario load() {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(password);
		return usuario;
	}
	
	public String save(Usuario user){
		//TODO
		return "";
		 
	}
}
