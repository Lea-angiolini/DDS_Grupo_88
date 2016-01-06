package Grupo88.Detalles;

import java.util.ArrayList;

import master.ErrorPage;
import master.RegisteredPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import Grupo88.Componentes.ListaDeRecetas;
import Grupo88.GestionarGrupos.GestionarGrupos;
import Grupo88.GestionarGrupos.NegocioGrupos;
import Grupo88.GestionarGrupos.PanelAdherirse;
import ObjetosDB.Grupo;
import ObjetosDB.Receta;
import ObjetosDB.Usuario;

public class DetalleGrupo extends RegisteredPage {

	private static final long serialVersionUID = -4226255942815446132L;
	@SuppressWarnings("unused")
	private FrmDetalleGrupo frmDetalleGrupo;
	private StringValue idGrupo;
	private Grupo grupo;
	private DetalleGrupo pagina;
	private NegocioGrupos negocio;
	
	public DetalleGrupo(final PageParameters parameters){
		super();
		pagina = this;
		negocio = new NegocioGrupos(getSessionUser());
		
		if(parameters.getNamedKeys().contains("idGrupo")){
			idGrupo = parameters.get("idGrupo");
		
	
		grupo = negocio.grupoPorId(idGrupo.toInt());
		
		if(grupo == null){
			 setResponsePage(new ErrorPage("No se encontro el Grupo"));
			 return;
		}
		
		if(!negocio.admiteUsuario(grupo, getUsuarioActual())){
			setResponsePage(new PanelAdherirse(negocio,grupo));
			return;
		}
		
		add(new FrmDetalleGrupo("frmDetalleGrupo"));
		
	}	
	}
	

	private class FrmDetalleGrupo extends Form<Object>{

		private static final long serialVersionUID = -2951767968039503061L;

		public FrmDetalleGrupo(String id) {
			super(id);
			
			add(new Label("nombreGrupo", grupo.getNombre()));
			
			add(new ListaDeRecetas("listaRecetas",new ArrayList<Receta>(grupo.obtenerRecetas()),getUsuarioActual(), pagina));
			
			RepeatingView usuariosGrupo = new RepeatingView("listaUsuarios");
			
			for (Usuario usuario : grupo.obtenerUsuarios()){
				
				AbstractItem item = new AbstractItem(usuariosGrupo.newChildId());
				
				item.add(new Label("unUsuario",usuario.getUsername()));
				
				usuariosGrupo.add(item);
			}
			
			add(usuariosGrupo);
			
			
			add(new Link<Object>("dejarGrupo"){
				
				private static final long serialVersionUID = 9034426688328890681L;

				@Override
				public void onClick() {
					negocio.sacarUsuario(getUsuarioActual(), grupo);
					setResponsePage(GestionarGrupos.class);
				}
			});
			
			add(new Link<Object>("volver"){
				
				private static final long serialVersionUID = -2571727525708390025L;

				@Override
				public void onClick() {
					setResponsePage(GestionarGrupos.class);
				}
			});
		}
	
	}
}