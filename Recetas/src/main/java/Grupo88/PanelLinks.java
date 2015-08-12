package Grupo88;

import objetosWicket.ModelUsuario;
import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class PanelLinks extends Panel {

	ModelUsuario mUsuario;
	SesionUsuario sesion = (SesionUsuario)getSession();
	
	public PanelLinks(String id) {
		super(id);
		add(new FrmLinks("FrmLinks"));
		
	}

	public class FrmLinks extends Form {
		
		public FrmLinks(String id) {
			super(id);
			
			add(new Link("recetas"){
				
				public void onClick() {
					setResponsePage(BuscarReceta.class);
				}
			});
			
			add(new Link("perfil"){
				
				public void onClick() {
					setResponsePage(GestionarPerfil.class);
				}
			});
			
			add(new Link("grupos"){
				
				public void onClick() {
					setResponsePage(GestionarGrupos.class);
				}
			});
			
			add(new Link("misRecetas"){
				
				public void onClick() {
					setResponsePage(MisRecetas.class);
				}
			});
			
			add(new Link("estadisticas"){
				
				public void onClick() {
					setResponsePage(Estadisticas.class);
				}
			});

		}
	}	
}