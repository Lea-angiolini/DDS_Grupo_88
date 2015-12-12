package Grupo88.GestionarGrupos;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

import Database.DAOGrupos;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Grupo;
import master.ErrorPage;
import master.RegisteredPage;

public class CrearNuevoGrupo extends RegisteredPage{

	private static final long serialVersionUID = 6553979241575620871L;
	private FormCrearNuevoGrupo formCrearNuevoGrupo;
	private NegocioGrupos negocio;
	public CrearNuevoGrupo() {
		negocio = new NegocioGrupos(getSessionBD());
		add(formCrearNuevoGrupo = new FormCrearNuevoGrupo("formCrearNuevoGrupo"));
		
	}
	
	private class FormCrearNuevoGrupo extends Form<Object>{

		private static final long serialVersionUID = 2888529345683241531L;
		
		private TextField<String> nombreGrupo;
		private TextField<String> detalle;
		public FormCrearNuevoGrupo(String id) {
			super(id);
	
			final Grupo nuevoGrupo = new Grupo(0,"","");
			nuevoGrupo.setCreador(getUsuarioActual());
			add(nombreGrupo = new TextField<String>("nombreGrupoNuevo", new PropertyModel<String>(nuevoGrupo, "nombre")));
			add(detalle = new TextField<String>("detalleGrupoNuevo", new PropertyModel<String>(nuevoGrupo, "detalle")));
			
			nombreGrupo.add(new StringValidator(3, 30));
			detalle.add(new StringValidator(1,255));
			
			add(new EmptyPanel("etiquetaConf"));
			add(new Button("btnConfirmarNvoGrupo") {
	
				private static final long serialVersionUID = -2840007213453451497L;
	
				@Override
				public void onSubmit() {
					if(!negocio.nuevoGrupo(nuevoGrupo, getUsuarioActual()))
						formCrearNuevoGrupo.addOrReplace(new Label("etiquetaConf", negocio.getError()));
					else{
						setResponsePage(new ErrorPage("¡Su grupo ha sido creado!"));
					}
	
				}
				
			});
			
			add(new Link("cancelar"){
				@Override
				public void onClick() {
					setResponsePage(Inicio.class);
				}
			});
		}
	}
}
