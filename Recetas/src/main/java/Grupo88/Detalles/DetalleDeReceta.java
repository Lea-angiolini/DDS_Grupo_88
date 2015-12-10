package Grupo88.Detalles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import master.ErrorPage;
import master.MasterPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.string.StringValueConversionException;
import org.hibernate.exception.ConstraintViolationException;

import Database.DAOCalificacion;
import Database.DAOConfirmar;
import Database.DAOGrupos;
import Database.DAORecetas;
import Database.DAOTipoReceta;
import Database.DBExeption;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Calificacion;
import ObjetosDB.Condimentos;
import ObjetosDB.Confirmacion;
import ObjetosDB.Grupo;
import ObjetosDB.Pasos;
import ObjetosDB.Receta;
import ObjetosDB.Receta_Ingrediente;
import ObjetosDB.TipoReceta;
import ObjetosDB.Usuario;

public class DetalleDeReceta extends MasterPage {

	private static final long serialVersionUID = 7898970758684629351L;

	private DAORecetas daoreceta;
	private Usuario user;
	private StringValue idReceta;
	private Receta receta;
	
	public DetalleDeReceta(final PageParameters parameters){
		super();
		
		iniciar(parameters);
		
		add(new Link("volver") {
			@Override
			public void onClick() {
					setResponsePage(Inicio.class);
			}
		});
		
		}
	
	@SuppressWarnings("unchecked")
	public DetalleDeReceta(final PageParameters parameters, final MasterPage pagina){
		super();
		iniciar(parameters);
		
		add(new Link("volver") {
			@Override
			public void onClick() {
				setResponsePage(pagina);
			}
		});

		}
	

	private void iniciar(PageParameters parameters){
		
		daoreceta = new DAORecetas(getSessionBD());
		
		user = getUsuarioActual();
		receta = null;
		
		if(parameters.getNamedKeys().contains("idReceta")){
			idReceta = parameters.get("idReceta");
			try {
				receta = daoreceta.get(idReceta.toInt());
			} catch (NullPointerException e){
				setResponsePage(ErrorPage.ErrorIngresoInvalidoDatos());
				return;
			}
			catch (StringValueConversionException e) {
				setResponsePage(ErrorPage.ErrorIngresoInvalidoDatos());
				return;
				
			} catch (DBExeption e) {
				setResponsePage(ErrorPage.ErrorEnLaDB());
				return;
			}
		
		add(new FrmDetalleDeReceta("FrmDetalleDeReceta"));
		
		if(getUsuarioActual().getUsername() != "Invitado"){
			add(new FormCalificar("formCalificar",getSessionBD(),receta,getUsuarioActual()));
			add(new FormConfirmar("formConfirmar",getSessionBD(),getUsuarioActual(),receta));
			add(new FormCompartir("formCompartir",getSessionBD(),getUsuarioActual(),receta));
		}
		else{
			add(new EmptyPanel("formCalificar"));
			add(new EmptyPanel("formConfirmar"));
			add(new EmptyPanel("formCompartir"));
		}
		
	}
	}
	private class FrmDetalleDeReceta extends Form<Object> {


	private static final long serialVersionUID = -1393638805587049063L;

		public FrmDetalleDeReceta(String id) {
			super(id);
			
			add(new Label("Nombre",receta.getNombre()));
			add(new Label("dif",receta.getDificultad().getDificultad()));
			add(new Label("tem",receta.getTemporada().getTemporada()));
			add(new Label("cal", receta.getCalorias()));
			add(new Image("imgPrinc", new DynamicImageResource(){
	
				private static final long serialVersionUID = -296773244831553413L;

				@Override
				protected byte[] getImageData(Attributes attributes) {
					return receta.getFotoPrincipal();
				}
			}));

			RepeatingView ingredientes = new RepeatingView("listaIngredientes");

			for (Receta_Ingrediente ingrediente : receta.getRelacionIngredientes()) {
				
				AbstractItem item = new AbstractItem(ingredientes.newChildId());
						
				item.add(new Label("unIngrediente", ingrediente.getIngrediente().getIngrediente()));
				ingredientes.add(item);
				
			}

			add(ingredientes);
			
			RepeatingView condimentos = new RepeatingView("listaCondimentos");
			
			for (Condimentos condimento : receta.getCondimentos()) {
				
				AbstractItem item = new AbstractItem(condimentos.newChildId());
						
				item.add(new Label("unCondimento", condimento.getCondimento()));
				condimentos.add(item);
				
			}
			
			add(condimentos);
			
			RepeatingView pasos = new RepeatingView("pasos");
			
			int i = 0;
			for (final Pasos paso : receta.getPasos()) {
				
				AbstractItem item = new AbstractItem(pasos.newChildId());
				i++;
				item.add(new Label("nro","Paso "+i+":"));		
				item.add(new Label("paso", paso.getDescripcionPaso()));
				item.add(new Image("img", new DynamicImageResource(){
					
					private static final long serialVersionUID = 4335079479260519834L;

					@Override
					protected byte[] getImageData(Attributes attributes) {
						return paso.getImagen();
					}
				}));
				pasos.add(item);
				
			}
			add(pasos);
		}

		@Override
		protected void onSubmit() {
			// Va a conectarse con BD y comprobar las validaciones
			super.onSubmit();
		}
	}
}