package Grupo88.GestionarGrupos;

import master.RegisteredPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import Database.Browser;
import Grupo88.Componentes.ListaDeRecetas;
import ObjetosDB.Grupo;
import ObjetosDB.Usuario;

public class DetalleGrupo extends RegisteredPage {

	// private TextField<String> txtUsuario;
	// private PasswordTextField txtPassword;
	//
	private FrmDetalleGrupo frmDetalleGrupo;
	private StringValue idGrupo;
	private Grupo grupo;
	
	
	public DetalleGrupo(final PageParameters parameters){
		super();
		//getMenuPanel().setVisible(false);
		if(parameters.getNamedKeys().contains("idGrupo")){
			idGrupo = parameters.get("idGrupo");
		
		grupo = Browser.obtenerGrupo(idGrupo.toInt());
		add(new FrmDetalleGrupo("frmDetalleGrupo"));
		
	}	
	}
	
	
	private class FrmDetalleGrupo extends Form{
		public FrmDetalleGrupo(String id) {
			super(id);
			
			add(new Label("nombreGrupo", grupo.getNombre()));
			add(new ListaDeRecetas("listaRecetas",grupo.obtenerRecetas(),getUsuarioActual()));
			
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