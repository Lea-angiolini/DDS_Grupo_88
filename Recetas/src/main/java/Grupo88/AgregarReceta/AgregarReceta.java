package Grupo88.AgregarReceta;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import master.ErrorPage;
import master.RegisteredPage;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.validator.StringValidator;

import Grupo88.Componentes.DropList;
import Grupo88.MisRecetas.MisRecetas;
import ObjetosDB.Condimentos;
import ObjetosDB.Dificultades;
import ObjetosDB.Ingredientes;
import ObjetosDB.Pasos;
import ObjetosDB.Receta;
import ObjetosDB.Temporadas;
import ObjetosDB.TipoReceta;
import ObjetosDB.Usuario;

public class AgregarReceta extends RegisteredPage {
	

	private static final long serialVersionUID = 1L;
	private Usuario user;
	private final Receta nuevareceta;
	private List<Fragmento> fragmentos;
	private NegocioAgregarReceta negocio;

	public AgregarReceta(){
		super();
		
		user =  getUsuarioActual();
		nuevareceta = new Receta();
		nuevareceta.setCreador(user);
		fragmentos = new ArrayList<Fragmento>();
		negocio = new NegocioAgregarReceta(getSessionBD());
		
		generarFragmentos();
		
		add(fragmentos.get(0));
		
		add(new FeedbackPanel("feedback").setOutputMarkupId(true));
		
	}
	
	private void generarFragmentos(){
		nuevareceta.setPasos(new ArrayList<Pasos>());
		fragmentos.add(new Fragmento("areaForms","fragmentoInicial",this,new FrmDatosReceta("frmDatosBasicos")));
		for(int i = 1; i<= 5; i++){
			Pasos paso;
			nuevareceta.agregarPaso(paso = new Pasos(i-1, ""));
			paso.setReceta(nuevareceta);
			fragmentos.add(new Fragmento("areaForms","fragmentoPaso",pagina(), new FrmPaso("frmPasos",i)));
		}
	}
	
	protected AgregarReceta pagina(){
		return this;
	}
	
	public class Fragmento extends Fragment{

		private static final long serialVersionUID = 1L;

		public Fragmento(String id, String markupId, MarkupContainer markupProvider, Form<?> form) {
			super(id, markupId, markupProvider);
			add(form);
		}
	}
	
	
	
	public class FrmDatosReceta extends Form<Object> {

		private static final long serialVersionUID = 1L;
		private FileUploadField fileUpload;
		private TextField<String> nombreReceta;
		private TextArea<String> descripcion;
		private DropDownChoice<Ingredientes> ingPrinc;
		private DropDownChoice<Temporadas> temporada;
		private DropDownChoice<Dificultades> dificultad;
		private DropDownChoice<TipoReceta> tiposReceta;
		private DropList<Ingredientes> dropIng;
		private DropList<Condimentos> dropCond;
		
		public FrmDatosReceta(String id) {
			super(id);
			
			if(!negocio.cargarListas())
				setResponsePage(ErrorPage.ErrorCargaDatos());
			
			
			add(nombreReceta = new TextField<String>("nombreReceta", new PropertyModel<String>(nuevareceta, "nombre")));
			add(descripcion = new TextArea<String>("descripcion", new PropertyModel<String>(nuevareceta, "detalle")));
			add(ingPrinc = new DropDownChoice<Ingredientes>("ingPrinc", new PropertyModel<Ingredientes>(nuevareceta, "ingredientePrincipal"), negocio.getTodosIngredientes(), new ChoiceRenderer<Ingredientes>("ingrediente", "idIngrediente")));
			add(temporada = new DropDownChoice<Temporadas>("temporada", new PropertyModel<Temporadas>(nuevareceta, "temporada"), negocio.getTodasTemporadas(), new ChoiceRenderer<Temporadas>("temporada", "idTemporada")));
			add(dificultad = new DropDownChoice<Dificultades>("dificultad", new PropertyModel<Dificultades>(nuevareceta, "dificultad"),negocio.getTodasDificultades(), new ChoiceRenderer<Dificultades>("dificultad", "idDificultad")));
			add(tiposReceta = new DropDownChoice<TipoReceta>("tipoRecomendado", new PropertyModel<TipoReceta>(nuevareceta, "tipoReceta"), negocio.getTodosTipoReceta(), new ChoiceRenderer<TipoReceta>("descripcion","idTipoReceta")));
			add(fileUpload = new FileUploadField("fileUpload"));
			add(dropIng = new DropList<Ingredientes>("dropIngredientes",negocio.getTodosIngredientes()));
			add(dropCond = new DropList<Condimentos>("dropCondimentos",negocio.getTodosCondimentos()));
			
			nombreReceta.setRequired(true);
			descripcion.setRequired(true);
			ingPrinc.setRequired(true);
			ingPrinc.setNullValid(true);
			dificultad.setRequired(true);
			dificultad.setNullValid(true);
			temporada.setRequired(true);
			temporada.setNullValid(true);
			tiposReceta.setRequired(true);
			tiposReceta.setNullValid(true);
			fileUpload.setRequired(true);
		
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			if(!negocio.cargarIngyCond(nuevareceta, dropIng.getElegidos(), dropCond.getElegidos())){
				error("Debe elegir un ingrediente minimo");
				return;
			}
				
			if(fileUpload.getFileUpload() != null){		
				try {
					
					if(ImageIO.read(fileUpload.getFileUpload().getInputStream()) == null){
						error("Formato de archivo no soportado");
						return;
					}
					
					nuevareceta.setFotoPrincipal(fileUpload.getFileUpload().getBytes());
					
				} catch (IOException e) {
					error("Formato de archivo no soportado");
					return;
				}
			}
			
			pagina().addOrReplace(fragmentos.get(1));
		}
	}
	
	public class FrmPaso extends Form<Object> {
		
		private static final long serialVersionUID = 1L;
		private TextArea<String> detallePaso;
		private int idFrmPaso;
		private FileUploadField fileUpload;
		private FileUpload imagenSubida;
		private Image imagen;
		public FrmPaso(String id,final int idPaso) {
			super(id);
			idFrmPaso = idPaso;
			
			StringValidator vText = new StringValidator(5, 2000);
			add(new Label("numPaso",idPaso));
			add(detallePaso = new TextArea<String>("paso", new PropertyModel<String>(nuevareceta.getPasos().get(idPaso-1), "descripcionPaso")));
			add(imagen = new Image("img", new SharedResourceReference(AgregarReceta.class, "default.jpg")));
			add(fileUpload = new FileUploadField("fileUpload"));
			
			detallePaso.add(vText);
			detallePaso.setRequired(true);
			setMultiPart(true);
			setMaxSize(Bytes.megabytes(15));
			
			imagen.setOutputMarkupId(true);	
			
			fileUpload.setOutputMarkupId(true);
			
			
			
			add(new Label ("sigPaso", (idPaso<5) ? "Siguiente Paso" : "Finalizar"));
			
			add(new Link<Object>("pasoAnt"){

				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					pagina().addOrReplace(fragmentos.get(idFrmPaso-1));
				}
			});
			
		}
		
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		
		
		if(fileUpload.getFileUpload() != null){
		try {
			imagenSubida = fileUpload.getFileUpload();
			
			if(ImageIO.read(imagenSubida.getInputStream()) == null){
				error("Formato de archivo no soportado");
				return;
			}
			else
			{
				
				Image newImage;
				imagen.replaceWith(newImage = new Image("img",new DynamicImageResource() {

					private static final long serialVersionUID = 1L;

					@Override
					protected byte[] getImageData(Attributes attributes) {
						return imagenSubida.getBytes();
					}
				}));
				imagen = newImage;
				nuevareceta.getPasos().get(idFrmPaso-1).setImagen(imagenSubida.getBytes());
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
			error("Formato de archivo no soportado");
			return;
			
			
		} catch (IOException e) {
			e.printStackTrace();
			error("Formato de archivo no soportado");
			return;
			
			
		}}
		
		if(idFrmPaso < fragmentos.size()-1){
		pagina().addOrReplace(fragmentos.get(idFrmPaso+1));	
		}
		else{
			if(negocio.guardarReceta(nuevareceta)){
				setResponsePage(MisRecetas.class);
			}
				
			error("Algo andubo mal.. Intente de vuelta por favor");
			}
		}
	}
}