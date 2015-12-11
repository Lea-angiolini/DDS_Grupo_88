package Grupo88.GestionarPerfil;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import Database.DAOUsuarios;
import ObjetosDB.Usuario;

public class NegocioGestionarPerfil {

	private DAOUsuarios daoUsuarios;
	
public NegocioGestionarPerfil(Session session){
	daoUsuarios = new DAOUsuarios(session);
}

public void guardarUsuario(Usuario usuario) throws ConstraintViolationException, javax.validation.ConstraintViolationException, Exception{
	daoUsuarios.saveOrUpdate(usuario);
	
}
	 
}
