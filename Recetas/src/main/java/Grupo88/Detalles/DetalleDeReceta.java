package Grupo88.Detalles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import master.ErrorPage;
import master.MasterPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.string.StringValueConversionException;

import Database.Browser;
import Database.DAORecetas;
import Database.DBExeption;
import ObjetosDB.Condimentos;
import ObjetosDB.Grupo;
import ObjetosDB.Ingredientes;
import ObjetosDB.Pasos;
import ObjetosDB.Receta;
import ObjetosDB.Receta_Ingrediente;
import ObjetosDB.Usuario;

public class DetalleDeReceta extends MasterPage {

	// private TextField<String> txtUsuario;
	// private PasswordTextField txtPassword;
	//
	private FrmDetalleDeReceta frmDetalleDeReceta;
	private DAORecetas daoreceta;
	public DetalleDeReceta(final PageParameters parameters){
		super();
		//getMenuPanel().setVisible(false);
		
		final Usuario user = getUsuarioActual();
		final StringValue idReceta;
		final Receta receta;
		daoreceta = new DAORecetas(getSessionBD());
		
		if(parameters.getNamedKeys().contains("idReceta")){
			idReceta = parameters.get("idReceta");
			try {
				receta = daoreceta.get(idReceta.toInt());
			} catch (NullPointerException e){
				setResponsePage(new ErrorPage(e.getMessage()));
				return;
			}
			catch (StringValueConversionException e) {
				// TODO Auto-generated catch block
				setResponsePage(new ErrorPage(e.getMessage()));
				return;
			} catch (DBExeption e) {
				// TODO Auto-generated catch block
				setResponsePage(new ErrorPage(e.getMessage()));
				return;
			}
			
//			JOptionPane.showMessageDialog(null, receta.getDificultad().getDificultad());
			//receta = Browser.cargarReceta(idReceta.toInt(), user);
		//user.cargarGrupos();
		
		add(generar(user,idReceta.toInt()));
		
		add(frmDetalleDeReceta = new FrmDetalleDeReceta("FrmDetalleDeReceta"));
		//new CheckBoxMultipleChoice
		frmDetalleDeReceta.add(new Label("Nombre",receta.getNombre()));
		EmptyPanel botonCal;
		if(receta.getCalificacion() == -1){
			frmDetalleDeReceta.add(new EmptyPanel("calificacion"));
			frmDetalleDeReceta.add(botonCal = new EmptyPanel("confCalificacion"));
			botonCal.setVisible(false);
		}
		else{
		frmDetalleDeReceta.add(new RadioChoice<Integer>("calificacion",new PropertyModel<Integer>(receta,"calificacion"),Arrays.asList(1,2,3,4,5)));
		
		frmDetalleDeReceta.add(new Button("confCalificacion"){
			@Override
			public void onSubmit() {
				// TODO Auto-generated method stub
				super.onSubmit();
				receta.calificar(user);
			}
		});
		}
		
		frmDetalleDeReceta.add(new Label("dif",receta.getDificultad().getDificultad()));
		frmDetalleDeReceta.add(new Label("tem",receta.getTemporada().getTemporada()));
		frmDetalleDeReceta.add(new Image("imgPrinc", new DynamicImageResource(){
			@Override
			protected byte[] getImageData(Attributes attributes) {
				// TODO Auto-generated method stub
				return receta.getFotoPrincipal();
			}
		}));
		
		RepeatingView ingredientes = new RepeatingView("listaIngredientes");
		
		for (Receta_Ingrediente ingrediente : receta.getRelacionIngredientes()) {
			
			AbstractItem item = new AbstractItem(ingredientes.newChildId());
					
			item.add(new Label("unIngrediente", ingrediente.getIngrediente().getIngrediente()));
			ingredientes.add(item);
			
		}
		
		frmDetalleDeReceta.add(ingredientes);
		
		RepeatingView condimentos = new RepeatingView("listaCondimentos");
		
		for (Condimentos condimento : receta.getCondimentos()) {
			
			AbstractItem item = new AbstractItem(condimentos.newChildId());
					
			item.add(new Label("unCondimento", condimento.getCondimento()));
			condimentos.add(item);
			
		}
		
		frmDetalleDeReceta.add(condimentos);
		
		RepeatingView pasos = new RepeatingView("pasos");
		
		int i = 0;
		for (final Pasos paso : receta.getPasos()) {
			
			AbstractItem item = new AbstractItem(pasos.newChildId());
			i++;
			item.add(new Label("nro","Paso "+i+":"));		
			item.add(new Label("paso", paso.getDescripcionPaso()));
			item.add(new Image("img", new DynamicImageResource(){
				@Override
				protected byte[] getImageData(Attributes attributes) {
					// TODO Auto-generated method stub
					return paso.getImagen();
				}
			}));
			pasos.add(item);
			
		}
		
		frmDetalleDeReceta.add(pasos);
		
		frmDetalleDeReceta.add(new Link("confirmar"){
			
			public void onClick() {
				final PageParameters pars = new PageParameters();
				pars.add("idReceta",idReceta);
				Browser.agregarAHistorial(idReceta.toInt(),user);
				setResponsePage(DetalleDeReceta.class,pars);
			}
		}.setVisible(SesionUsuario.get().estaLogueado()));
		
		frmDetalleDeReceta.add(new AjaxLink("compartir"){
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				
				target.appendJavaScript("abrirDialogo();");
			}
		}.setVisible(SesionUsuario.get().estaLogueado()));
		}
	}

	public RepeatingView generar(Usuario user, final int idReceta){
		RepeatingView condiciones = new RepeatingView("iterador");
		int i = 1;
		for (final Grupo grupo : user.getGrupos()){

			final AbstractItem item = new AbstractItem(condiciones.newChildId());
			
			item.add(new Label("nombre",grupo.getNombre()));
			
			if(grupo.tieneReceta(idReceta)){
				final AbstractItem btnCompGrupo = new AbstractItem("bton");
				btnCompGrupo.add(new AttributeModifier("class", "btn btn-success disabled"));
				btnCompGrupo.add(new AttributeAppender("id", i));
				btnCompGrupo.add(new Label("textBton", "Compartido").add(new AttributeAppender("id", i)));
				item.add(btnCompGrupo);
			}
			else{
				final int it = i;
				 final AjaxLink btnCompGrupo = new AjaxLink("bton") {
					@Override
					public void onClick(AjaxRequestTarget target) {
						// TODO Auto-generated method stub
						if(grupo.agregarReceta(idReceta))
						{
						target.prependJavaScript("cambiarClase('"+target.getLastFocusedElementId()+"');");
						target.prependJavaScript("cambiarTexto("+it+");");
						//this.onComponentTag(tag);
						}
					}
					@Override
					protected void onBeforeRender() {
						// TODO Auto-generated method stub
						super.onBeforeRender();
						//this.add(new AttributeAppender("id", it));
					}
				};
				//btnCompGrupo.add(new AttributeModifier("onclick", "cambiarTexto("+i+")"));
				btnCompGrupo.add(new AttributeModifier("class", "btn btn-primary"));
				btnCompGrupo.add(new Label("textBton", "Compartir").add(new AttributeAppender("id", i)));
				item.add(btnCompGrupo);
				
			}
				
			
			
			condiciones.add(item);
			i++;
		}
		
		return condiciones;
	}

	public class GruposElegidos{
		private Collection<Grupo> gruposelegidos;
		
		public void setGruposelegidos(List<Grupo> gruposelegidos) {
			this.gruposelegidos = gruposelegidos;
		}
		public Collection<Grupo> getGruposelegidos() {
			return gruposelegidos;
		}
		public GruposElegidos() {
			gruposelegidos = new ArrayList<Grupo>();
		}
	}
	public class FrmDetalleDeReceta extends Form {

		public FrmDetalleDeReceta(String id) {
			super(id);
			setDefaultModel(new CompoundPropertyModel(this));

		}

		@Override
		protected void onSubmit() {
			// Va a conectarse con BD y comprobar las validaciones
			super.onSubmit();
		}
	}
}