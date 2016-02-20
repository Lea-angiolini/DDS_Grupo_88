package Grupo88.GestionarPerfil;

import java.io.Serializable;

import master.Negocio;
import objetosWicket.SesionUsuario;
import Database.DAOUsuarios;
import ObjetosDB.Usuario;

	public class NegocioGestionarPerfil extends Negocio implements Serializable {
	
		private static final long serialVersionUID = 7769434769016840534L;
		private DAOUsuarios daoUsuarios;
		
	public NegocioGestionarPerfil(SesionUsuario sesion){
		super(sesion);
		daoUsuarios = new DAOUsuarios(sesion.getSessionDB());
	}
	
	public boolean guardarUsuario(Usuario usuario){
		try {
			daoUsuarios.saveOrUpdate(usuario);
			return true;
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return false;
		}
		
	}
	 
}
