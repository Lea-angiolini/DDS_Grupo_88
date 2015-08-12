package Grupo88;

import objetosWicket.SesionUsuario;
import Grupo88.MisRecetas.FrmMisRecetas;
import ObjetosDB.Usuario;
import master.MasterPage;

public class Estadisticas extends MasterPage{
	private FrmMisRecetas frmMisRecetas;
	private Usuario user = ((SesionUsuario)getSession()).getUsuario().getObject();
	
	public Estadisticas(){
		super();
		getMenuPanel().setVisible(false);
		
		//add(frmMisRecetas = new FrmMisRecetas("divEstadisticas"));
		
	}
}
