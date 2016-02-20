package Grupo88.Detalles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import ObjetosDB.Confirmacion;
import ObjetosDB.Receta;
import ObjetosDB.TipoReceta;
import ObjetosDB.Usuario;

public class FormConfirmarPanel extends Panel{

	private static final long serialVersionUID = 5866866909620842478L;

	private Confirmacion confirmacion;
	private NegocioRecetas negocio;
	private ArrayList<TipoReceta> todosTipoReceta;
	private Receta receta;
	private Usuario user;
	
	public FormConfirmarPanel(String id, Usuario use, Receta rec, NegocioRecetas negocio) {
		super(id);
		
		this.user = use;
		this.receta = rec;
		this.negocio = negocio;
		
		add(new FormConfirmar(id, use, rec));
	}

	
	private class FormConfirmar extends Form<Object>{
	
		private static final long serialVersionUID = 3482226522671170037L;

		public FormConfirmar(String id, Usuario use, Receta rec) {
			super(id);
			
			confirmacion = new Confirmacion();
			todosTipoReceta= negocio.getTiposDeReceta();
	
			
			add(new RadioChoice<TipoReceta>("comida", new PropertyModel<TipoReceta>(confirmacion, "tipoReceta"), todosTipoReceta,new ChoiceRenderer<TipoReceta>("descripcion","idTipoReceta")));
			add(new Button("confirmar"){
				
				private static final long serialVersionUID = 8149758093736016771L;
	
				public void onSubmit() {
					super.onSubmit();
					final PageParameters pars = new PageParameters();
					pars.add("idReceta",receta.getIdreceta());
					confirmacion.setReceta(receta);
					confirmacion.setUser(user);
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					confirmacion.setFecha(dateFormat.format(new Date()));
						
					if(negocio.guardarCOnfirmacion(confirmacion))
						setResponsePage(DetalleDeReceta.class,pars);
					else
						info(negocio.getError());
					
				}
			});
	
		}
	}
}