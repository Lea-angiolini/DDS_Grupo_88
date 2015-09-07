package Grupo88.AgregarReceta;

import java.awt.Event;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import master.RegisteredPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior.AjaxFormSubmitter;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.apache.wicket.request.resource.IResource.Attributes;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.validation.validator.StringValidator;

import Database.Browser;
import Grupo88.Componentes.DropList;
import Grupo88.MisRecetas.MisRecetas;
import ObjetosDB.Condimentos;
import ObjetosDB.Dificultades;
import ObjetosDB.Ingredientes;
import ObjetosDB.Pasos;
import ObjetosDB.RecetaU;
import ObjetosDB.Temporadas;
import ObjetosDB.Usuario;

public class AgregarReceta extends RegisteredPage {
	
	private SesionUsuario sesion = (SesionUsuario)getSession();
	private Usuario user = sesion.getUsuario().getObject();
	private final RecetaU nuevareceta = new RecetaU(-1, "", user.getUsername(), null, null, null,"",0);
	private List<Fragmento> fragmentos = new ArrayList<Fragmento>();
	private DropList<Ingredientes> dropIng;
	private DropList<Condimentos> dropCond;
	private static final ResourceReference RESOURCE_REF = new PackageResourceReference(AgregarReceta.class,
	        "default.jpg");

	//private Fragmento frm
	
	public AgregarReceta(){
		super();
		//getMenuPanel().setVisible(false);
		
		fragmentos.add(new Fragmento("areaForms","fragmentoInicial",this,new FrmDatosReceta("frmDatosBasicos")));
		for(int i = 1; i<= 5; i++){
			nuevareceta.agregarPaso(new Pasos(i, ""));
			fragmentos.add(new Fragmento("areaForms","fragmentoPaso",pagina(), new FrmPaso("frmPasos",i)));
		}
		add(fragmentos.get(0));
		
		add(new FeedbackPanel("feedback").setOutputMarkupId(true));
		
	}
	
	protected AgregarReceta pagina(){
		return this;
	}
	
	public class Fragmento extends Fragment{

		public Fragmento(String id, String markupId, MarkupContainer markupProvider, Form form) {
			super(id, markupId, markupProvider);
			// TODO Auto-generated constructor stub
			add(form);
		}
	}
	
	
	
	public class FrmDatosReceta extends Form {
		private FileUploadField fileUpload;
		public FrmDatosReceta(String id) {
			super(id);
			// TODO Auto-generated constructor stub
		//final TextField<String> nombre;
		add(new TextField<String>("nombreReceta", new PropertyModel<String>(nuevareceta, "nombre")));
		add(new TextArea<String>("descripcion", new PropertyModel<String>(nuevareceta, "detalle")));
		add(new DropDownChoice<Temporadas>("temporada", new PropertyModel<Temporadas>(nuevareceta, "temporada"), Browser.listaTemporadas(), new ChoiceRenderer<Temporadas>("temporada", "idTemporada")));
		add(new DropDownChoice<Dificultades>("dificultad", new PropertyModel<Dificultades>(nuevareceta, "dificultad"), Browser.listaDificultades(), new ChoiceRenderer<Dificultades>("dificultad", "idDificultad")));
		add(new DropDownChoice<Ingredientes>("ingPrinc", new PropertyModel<Ingredientes>(nuevareceta, "ingredientePrincipal"), Browser.listaIngredientes(), new ChoiceRenderer<Ingredientes>("ingrediente", "idIngrediente")));
		
		add(fileUpload = new FileUploadField("fileUpload"));
		fileUpload.setRequired(true);
		
		add(dropIng = new DropList<Ingredientes>("dropIngredientes",Browser.listaIngredientes()));
		add(dropCond = new DropList<Condimentos>("dropCondimentos",Browser.listaCondimentos()));
		}
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		pagina().addOrReplace(fragmentos.get(1));
		nuevareceta.setIngredientes(dropIng.getElegidos());
		nuevareceta.setCondimentos(dropCond.getElegidos());
		
		if(fileUpload.getFileUpload() != null){		
				try {
					if(ImageIO.read(fileUpload.getFileUpload().getInputStream()) == null){
						error("Formato de archivo no soportado");
						return;
					}
					else{
						nuevareceta.setFotoPrincipal(fileUpload.getFileUpload().getBytes());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		}
	}
	
	public class FrmPaso extends Form {
		
		private TextArea<String> detallePaso;
		private int idFrmPaso;
		private FileUploadField fileUpload;
		private FileUpload imagenSubida;
		private Image imagen;
		public FrmPaso(String id,final int idPaso) {
			super(id);
			// TODO Auto-generated constructor stub
			idFrmPaso = idPaso;
			
			StringValidator vText = new StringValidator(5, 12);
			add(new Label("numPaso",idPaso));
			add(detallePaso = new TextArea<String>("paso", new PropertyModel<String>(nuevareceta.getPasos().get(idPaso-1), "descripcionPaso")));
			detallePaso.add(vText);
			detallePaso.setRequired(true);
			setMultiPart(true);
			setMaxSize(Bytes.megabytes(15));
			
			add(imagen = new Image("img", new SharedResourceReference(AgregarReceta.class, "default.jpg")));
			imagen.setOutputMarkupId(true);	
			
			add(fileUpload = new FileUploadField("fileUpload"));
			fileUpload.setOutputMarkupId(true);
			
			
			
			add(new Label ("sigPaso", (idPaso<5) ? "Siguiente Paso" : "Finalizar"));
			
			add(new Link("pasoAnt"){
				@Override
				public void onClick() {
					// TODO Auto-generated method stub
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
					
					@Override
					protected byte[] getImageData(Attributes attributes) {
						// TODO Auto-generated method stub
						return imagenSubida.getBytes();
					}
				}));
				imagen = newImage;
				nuevareceta.getPasos().get(idFrmPaso-1).setImagen(imagenSubida.getBytes());
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		
		
		if(idFrmPaso < fragmentos.size()-1){
		pagina().addOrReplace(fragmentos.get(idFrmPaso+1));	
		}
		else{
			nuevareceta.guardarReceta();
			setResponsePage(MisRecetas.class);
			}
		}
	}
}