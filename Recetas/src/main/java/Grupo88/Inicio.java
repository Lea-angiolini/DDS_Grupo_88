package Grupo88;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;

import master.MasterPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.core.request.mapper.BookmarkableMapper;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.include.Include;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.eclipse.jetty.server.Server;
import org.apache.wicket.Session;

import Database.Browser;
import Grupo88.AltaUsuario.FrmAltaUsuario;
import Grupo88.Login.FrmLogin;
import ObjetosDB.Recetas;
import ObjetosDB.Recetas.Receta;
import ObjetosDB.Dificultades;
import ObjetosDB.GruposAlimenticios;
import ObjetosDB.Ingredientes;
import ObjetosDB.Temporadas;
import ObjetosDB.itemsABuscar;

public class Inicio extends MasterPage {

	private FrmInicio frmInicio;
	
	@SuppressWarnings("unchecked")
	public Inicio(){
		super();
		//getMenuPanel().setVisible(false);
		
		if(!((SesionUsuario)getSession()).estaLogueado())
		{
			setResponsePage(Login.class);
		}
		
		
		add(frmInicio = new FrmInicio("FrmInicio"));
		
		frmInicio.add(new Link("misRecetas"){
			
				public void onClick(){
				setResponsePage(GestionarRecetas.class);
			}
		});
		
		frmInicio.add(new Link("salir"){
			
				public void onClick() {
				setResponsePage(Login.class);
				
			}
		});
		
		frmInicio.add(new Link("historial"){
			
			public void onClick(){
			//setResponsePage(GestionarRecetas.class);
				Fragment fragment = new  FragmentoRecetasUsuario ("contentArea", "listaRecetas", frmInicio, "jorge");
				frmInicio.add(fragment);
			}
		});
		
		frmInicio.add(new Link("recetasPopulares"){
			
			public void onClick(){
			//setResponsePage(GestionarPerfil.class);
				Fragment fragment = new  FragmentoRecetasPopulares ("contentArea", "listaRecetas", frmInicio);
				frmInicio.add(fragment);
			}
		});
		
		
		frmInicio.add(new Link("buscarReceta"){
			
			public void onClick() {
				
				Fragment fragment = new  FragmentoBuscarRecetas ("contentArea", "buscarRecetas", frmInicio);
				frmInicio.add(fragment);
			}
		});

		frmInicio.add(new EmptyPanel("contentArea"));
		
	}
	
	public class FrmInicio extends Form {
		
		

		public FrmInicio(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));
			
		}
		
		@Override
		protected void onSubmit() {
			// Va a conectarse con BD y comprobar las validaciones
			super.onSubmit();
		}
	}
	
	@SuppressWarnings("unchecked")
	private DataView generarTabla(Recetas recetas){
	
		return (new DataView("tablaPopulares",  new ListDataProvider(recetas.ObtenerColeccionRecetas())){
			@Override
			protected void populateItem(Item item) {
				// TODO Auto-generated method stub
				
				final Receta recetas= (Receta) item.getModelObject();
				
				final PageParameters pars = new PageParameters();
				pars.add("idReceta",recetas.getIdreceta());
				
				Link bton = new Link("bt"){
					@Override
					public void onClick() {
						// TODO Auto-generated method stub
						setResponsePage(DetalleDeReceta.class,pars);
					}
					
				};
				
				
				//item.addOrReplace(new Label("indice",item.getIndex()));
				
				bton.addOrReplace(new Label("campo1",recetas.getNombre()));
				bton.addOrReplace(new Label("campo2",recetas.getCreador()));
				bton.addOrReplace(new Label("campo3",recetas.getDificultad().getDificultad()));
				
				item.add(bton);
				
			}
		});
	}
	
	 public class FragmentoRecetasPopulares extends Fragment {
	        public FragmentoRecetasPopulares(String id, String markupId,MarkupContainer markupPorvider) {
	        	
	        	super(id, markupId, markupPorvider);

	        	Recetas recetas = Browser.cargarRecetasPopulares();		
	    		
	        	markupPorvider.remove(id);
	        	
	        	add(new Label("nombreGrilla"," Recetas Más Populares"));
	    		add(generarTabla(recetas));
	            
	        }
	
	 }
	 
	 
	 public class FragmentoRecetasUsuario extends Fragment {
	        public FragmentoRecetasUsuario(String id, String markupId,MarkupContainer markupPorvider, String usuario) {
	        	
	        	super(id, markupId, markupPorvider);

	        	Recetas recetas = Browser.cargarRecetasUsuario(usuario);	
	    		
	        	markupPorvider.remove(id);
	        	
	        	add(new Label("nombreGrilla"," Historial"));
	        	add(generarTabla(recetas));
	            
	        }
	
	 }
	 
	 public class FragmentoRecetasBuscadas extends Fragment {
	        public FragmentoRecetasBuscadas(String id, String markupId,MarkupContainer markupPorvider,MarkupContainer MarkupActual, itemsABuscar queBuscar ) {
	        	
	        	super(id, markupId, markupPorvider);
	        	
	        	Recetas recetas = Browser.cargarRecetasBuscadas(queBuscar);	
	    		
	        	MarkupActual.remove(id);
	        	
	        	add(new Label("nombreGrilla"," Resultados"));
	        	add(generarTabla(recetas));
	            
	        }
	
	 }
	 
	 public class FragmentoBuscarRecetas extends Fragment {
	        @SuppressWarnings("unchecked")
			public FragmentoBuscarRecetas(String id, String markupId,MarkupContainer markupPorvider) {
	        	super(id, markupId, markupPorvider);
	        	
	        	markupPorvider.remove(id);
	        	add(new Label("nombreGrilla"," Buscar Receta"));
	        	
	        	final itemsABuscar items = new itemsABuscar();
	        		        	
	        	add(new DropDownChoice<Dificultades>("dificultad",new PropertyModel<Dificultades>(items,"dificultad"), Browser.listaDificultades(),new ChoiceRenderer("dificultad","idDificultad")));
	        	add(new DropDownChoice<Temporadas>("temporada",new PropertyModel<Temporadas>(items,"temporada"), Browser.listaTemporadas(),new ChoiceRenderer("temporada","idTemporada")));
	        	add(new DropDownChoice<Ingredientes>("ingrediente",new PropertyModel<Ingredientes>(items,"ingredientePrincipal"), Browser.listaIngredientes(),new ChoiceRenderer("ingrediente","idIngrediente")));
	        	add(new DropDownChoice<GruposAlimenticios>("grupoAlim",new PropertyModel<GruposAlimenticios>(items,"grupoAlimenticio"), Browser.listaGruposAlim(),new ChoiceRenderer("grupoAlim","idGrupoAlim")));
	        	add(new DropDownChoice<Integer>("calificaciones",new PropertyModel<Integer>(items, "calificacion"), Arrays.asList(1,2,3,4,5)));
	        	add(new NumberTextField<Integer>("caloriasMin", new PropertyModel<Integer>(items, "caloriasMin"), Integer.class));
	        	add(new NumberTextField<Integer>("caloriasMax", new PropertyModel<Integer>(items, "caloriasMax"), Integer.class));
		        
	        	final FragmentoBuscarRecetas fragmentoActual = this;
	        	
	        	add(new Button("botonBuscar") {
	        		
	        	public void onSubmit() {
	        		
	        		fragmentoActual.add( new  FragmentoRecetasBuscadas ("areaRecetas", "listaRecetas", frmInicio, fragmentoActual, items));

	        	}
	        	});
	        	
	        	add(new EmptyPanel("areaRecetas"));
	        }
	 }
}