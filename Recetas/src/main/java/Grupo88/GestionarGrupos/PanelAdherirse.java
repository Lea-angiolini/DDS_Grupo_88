package Grupo88.GestionarGrupos;

import master.ErrorPage;
import master.RegisteredPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import Grupo88.Detalles.DetalleGrupo;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Grupo;

public class PanelAdherirse extends RegisteredPage{
	
	private NegocioGrupos negocio;
	private Grupo grupo;
	public PanelAdherirse(NegocioGrupos negocio, Grupo grupo) {
		super();
		this.negocio = negocio;
		this.grupo = grupo;
		add(new FormPanelAdherirse("frmAdherise"));
		
    }
	
	private class FormPanelAdherirse extends Form{

		private static final long serialVersionUID = -3158424907182082622L;

		public FormPanelAdherirse(String id) {
			super(id);
			add(new Label("msg", "Usted no esta adherido al grupo"));
			
			add(new Link<Object>("adherirse"){
				@Override
				public void onClick() {
					if(!negocio.agregarUsuario(getUsuarioActual(), grupo))
						setResponsePage(ErrorPage.ErrorEnLaDB());
					
					PageParameters pars = new PageParameters();
					pars.add("idGrupo",grupo.getIdGrupo());
					setResponsePage(DetalleGrupo.class,pars);
				}
			});
			
			add(new Link<Object>("irInicio"){
				@Override
				public void onClick() {
					setResponsePage(Inicio.class);
				}
			});
		}
	}

}
