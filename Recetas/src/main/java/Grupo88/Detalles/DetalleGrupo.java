package Grupo88.Detalles;

import java.util.ArrayList;

import master.ErrorPage;
import master.RegisteredPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import Grupo88.Componentes.ListaDeRecetas;
import ObjetosDB.Grupo;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class DetalleGrupo extends RegisteredPage {

	private static final long serialVersionUID = -4226255942815446132L;
	// private TextField<String> txtUsuario;
	// private PasswordTextField txtPassword;
	//
	@SuppressWarnings("unused")
	private FrmDetalleGrupo frmDetalleGrupo;
	private StringValue idGrupo;
	private Grupo grupo;
	//private DAOGrupos daogrupos;
	
	public DetalleGrupo(final PageParameters parameters){
		super();
		//getMenuPanel().setVisible(false);
		if(parameters.getNamedKeys().contains("idGrupo")){
			idGrupo = parameters.get("idGrupo");
		
	
//		FactoryGrupo fabGrupo = new FactoryGrupo(getUsuarioActual());
		grupo = getUsuarioActual().getGrupoPorID(idGrupo.toInt());
		if(grupo == null){
			 setResponsePage(new ErrorPage("No se encontro el Grupo o no esta autorizado"));
			 return;
		}
	
		add(new FrmDetalleGrupo("frmDetalleGrupo"));
		
	}	
	}
	

	private class FrmDetalleGrupo extends Form<Object>{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2951767968039503061L;

		public FrmDetalleGrupo(String id) {
			super(id);
			
			add(new Label("nombreGrupo", grupo.getNombre()));
			
			add(new ListaDeRecetas("listaRecetas",new ArrayList<Receta>(grupo.obtenerRecetas()),getUsuarioActual()));
			
			RepeatingView usuariosGrupo = new RepeatingView("listaUsuarios");
			
			for (Usuario usuario : grupo.obtenerUsuarios()){
				
				AbstractItem item = new AbstractItem(usuariosGrupo.newChildId());
				
				item.add(new Label("unUsuario",usuario.getUsername()));
				
				usuariosGrupo.add(item);
			}
			
			add(usuariosGrupo);
		}
	
	}
}