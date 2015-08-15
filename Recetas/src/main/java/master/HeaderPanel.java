package master;



import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import Grupo88.Inicio;
import ObjetosDB.Usuario;

public class HeaderPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeaderPanel(String id) {
		super(id);
		
		if(SesionUsuario.exists() && SesionUsuario.get().estaLogueado())
		{
			Usuario user = SesionUsuario.get().getUsuario().getObject();
			
			addOrReplace(new Label("saludo", new PropertyModel<String>(user, "nombre"){
				@Override
				public String getObject() {
					return ". Bienvenido " + super.getObject();
					
				}
			}));
			
			addOrReplace(new Link("cerrarSesion"){
				@Override
				public void onClick() {
					// TODO Auto-generated method stub
					SesionUsuario.get().desloguearUsuario();
					setResponsePage(Inicio.class);
				}
			});
		}
		else {
			addOrReplace(new Label("saludo", Model.of("")));
			addOrReplace(new EmptyPanel("cerrarSesion"));
		}
	}
}
