package master;



import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import Grupo88.Inicio.Inicio;
import ObjetosDB.Usuario;

public class HeaderPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public HeaderPanel(String id) {
		super(id);
		
		if(SesionUsuario.exists() && SesionUsuario.get().estaLogueado())
		{
			Usuario user = SesionUsuario.get().getUsuario();
			
			addOrReplace(new Label("saludo", new PropertyModel<String>(user, "nombre"){

				private static final long serialVersionUID = 4120667460311423316L;

				@Override
				public String getObject() {
					return ". Bienvenido " + super.getObject();
					
				}
			}));
			
			addOrReplace(new Link<Object>("cerrarSesion"){

				private static final long serialVersionUID = 4390547793617658103L;

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
