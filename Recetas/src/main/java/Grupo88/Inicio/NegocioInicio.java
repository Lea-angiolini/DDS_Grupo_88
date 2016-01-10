package Grupo88.Inicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Database.DAORecetas;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;
import ObjetosDB.UsuarioNoRegistrado;
import objetosWicket.SesionUsuario;
import master.Negocio;

public class NegocioInicio extends Negocio implements Serializable {
	
	private static final long serialVersionUID = -1338084816827828098L;
	private DAORecetas daoRecetas;
	
	public NegocioInicio(SesionUsuario sesion) {
		super(sesion);
		 daoRecetas = new DAORecetas(sesion.getSessionDB());
	}
	
	public ArrayList<Receta> homePara(Usuario user){
		
		ArrayList<Receta> recetas;
		try {
			if(!(user instanceof UsuarioNoRegistrado)){
				recetas = (ArrayList<Receta>) daoRecetas.ultimasConfirmadas(user,10);
					if(recetas.size() != 0){
						return new ArrayList<Receta>(recetas);
					}
					
				recetas = (ArrayList<Receta>) daoRecetas.ultimasConsultadas(user, 10);
					if(recetas.size() != 0){
						return new ArrayList<Receta>(recetas);
					}
			}
			recetas = (ArrayList<Receta>) daoRecetas.mejoresCalificadas(10);
			return new ArrayList<Receta>(recetas);
		} catch (Exception e) {
			setError(manejador.tratarExcepcion(e));
			return new ArrayList<Receta>();
		}
	}
}