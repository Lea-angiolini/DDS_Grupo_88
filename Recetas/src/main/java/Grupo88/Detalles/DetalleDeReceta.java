package Grupo88.Detalles;

import java.awt.CheckboxGroup;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.CheckboxMultipleChoiceSelector;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
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
import Grupo88.BuscarReceta.BuscarReceta;
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
	private Confirmacion confirmacion;
	
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
				setResponsePage(new ErrorPage(e.getMessage()));
				return;
			}
			catch (StringValueConversionException e) {
				setResponsePage(new ErrorPage(e.getMessage()));
				return;
			} catch (DBExeption e) {
				setResponsePage(new ErrorPage(e.getMessage()));
				return;
			}
		
		add(new FrmDetalleDeReceta("FrmDetalleDeReceta"));

		add(new FormCalificar("formCalificar"));

		add(new FormConfirmar("formConfirmar"));
		
		add(new FormCompartir("formCompartir"));
		
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
	
	private class FormConfirmar extends Form{

		private static final long serialVersionUID = 3482226522671170037L;

		public FormConfirmar(String id) {
			super(id);
			
			confirmacion = new Confirmacion();
			try {
				add(new RadioChoice("comida", new PropertyModel(confirmacion, "tipoReceta"), (new DAOTipoReceta(getSessionBD()).findAll()),new ChoiceRenderer("descripcion","idTipoReceta")));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				setResponsePage(new ErrorPage("ups"));
			}
			add(new Button("confirmar"){
				
				private static final long serialVersionUID = 8149758093736016771L;

				public void onSubmit() {
					final PageParameters pars = new PageParameters();
					pars.add("idReceta",idReceta);
					DAOConfirmar daoConfirmar = new DAOConfirmar(getSessionBD());
					confirmacion.setReceta(receta);
					confirmacion.setUser(user);
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					confirmacion.setFecha(dateFormat.format(new Date()));
						
						try {
							daoConfirmar.save(confirmacion);
						} catch (ConstraintViolationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e.getMessage());
						} catch (javax.validation.ConstraintViolationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e.getMessage());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							setResponsePage(new ErrorPage("ups"));
						}
					
					setResponsePage(DetalleDeReceta.class,pars);
				}
			}.setVisible(SesionUsuario.get().estaLogueado()));

		}
	}

	private class FormCalificar extends Form{

		private static final long serialVersionUID = -7463688006174588993L;
		
		private DAOCalificacion daoCalificacion = new DAOCalificacion(getSessionBD());
		private DAOConfirmar daoConfirmar = new DAOConfirmar(getSessionBD());
		private Calificacion calificacion;
		
		public FormCalificar(String id) {
			super(id);
			
			try {
				calificacion = daoCalificacion.calificacionDe(receta, user);
				
				if(calificacion == null){
					calificacion = new Calificacion();
					calificacion.setReceta(receta);
					calificacion.setUserCalificador(user);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			
			EmptyPanel botonCal;
			try {
				if(!daoConfirmar.userConfirmo(receta, user)){
					add(new EmptyPanel("calificacion"));
					add(botonCal = new EmptyPanel("confCalificacion"));
					botonCal.setVisible(false);
				}
				else{
					
					
					
				add(new RadioChoice<Integer>("calificacion",new PropertyModel<Integer>(calificacion,"calificacion"),Arrays.asList(1,2,3,4,5)));
				
				add(new Button("confCalificacion"){

					private static final long serialVersionUID = -6871206486314240822L;

					@Override
					public void onSubmit() {
						super.onSubmit();
						try {
							daoCalificacion.saveOrUpdate(calificacion);
						} catch (ConstraintViolationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e.getMessage());
						} catch (javax.validation.ConstraintViolationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e.getMessage());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				});
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		}
	}

	private class FormCompartir extends Form{
		
		private DAOGrupos daoGrupos = new DAOGrupos(getSessionBD());
		public FormCompartir(String id) {
			super(id);
			
			final ArrayList<Grupo> gruposselect = new ArrayList<Grupo>();
			
			try {
				add(new CheckBoxMultipleChoice("grupos", new Model(gruposselect),new ArrayList(daoGrupos.gruposde(getUsuarioActual())),new ChoiceRenderer("nombre","idGrupo")));
			} catch (Exception e) {
				e.printStackTrace();
				add(new EmptyPanel("grupos"));
			}
			
			add(new Button("compartir"){
				@Override
				public void onSubmit() {
					super.onSubmit();
					
					for (Grupo grupo : gruposselect) {
						grupo.agregarReceta(receta);
						try {
							daoGrupos.saveOrUpdate(grupo);
						} catch (ConstraintViolationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (javax.validation.ConstraintViolationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			});
		}
	}
}