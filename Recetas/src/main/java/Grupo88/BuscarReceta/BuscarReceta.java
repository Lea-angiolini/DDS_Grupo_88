package Grupo88.BuscarReceta;

import java.util.ArrayList;

import master.ErrorPage;
import master.MasterPage;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.PropertyModel;

import Grupo88.Componentes.ListaDeRecetas;
import ObjetosDB.Dificultades;
import ObjetosDB.GruposAlimenticios;
import ObjetosDB.Ingredientes;
import ObjetosDB.Receta;
import ObjetosDB.Temporadas;
import ObjetosDB.itemsABuscar;

public class BuscarReceta extends MasterPage {
	
	private static final long serialVersionUID = -5692664542852073548L;
	private FrmBuscarReceta frmBuscarReceta;
	private BuscarReceta pagina;
	private NegocioBuscarReceta negocio;
	public BuscarReceta(){
		super();
		
		negocio = new NegocioBuscarReceta(getSessionUser());
		
		pagina = this;
		add(frmBuscarReceta = new FrmBuscarReceta("FrmBuscarReceta"));
		
		}
	
	private class FrmBuscarReceta extends Form<Object> {
		
		private static final long serialVersionUID = -1970941538448227225L;
		final itemsABuscar items = new itemsABuscar();
		public FrmBuscarReceta(String id) {
			super(id);			
			
			if(!negocio.cargarListas())
				setResponsePage(ErrorPage.ErrorCargaDatos());
			
			
			DropDownChoice<Dificultades> dropdownDificultades = new DropDownChoice<Dificultades>("dificultad",new PropertyModel<Dificultades>(items,"dificultad"), negocio.getDificultades() ,new ChoiceRenderer<Dificultades>("dificultad","idDificultad")){

				private static final long serialVersionUID = 1L;

				@Override
    			protected String getNullValidDisplayValue() {
    				return "Todas";
    			}
    		};
    		dropdownDificultades.setNullValid(true);
    		add(dropdownDificultades);
    		
    		
    		DropDownChoice<Ingredientes> dropdownIngredientes = new DropDownChoice<Ingredientes>("ingrediente",new PropertyModel<Ingredientes>(items,"ingredientePrincipal"), negocio.getIngredientes(),new ChoiceRenderer<Ingredientes>("ingrediente","idIngrediente")){

				private static final long serialVersionUID = 1L;

				protected String getNullValidDisplayValue() {
    				return "Todos";
    			}
    		};
    		dropdownIngredientes.setNullValid(true);
    		add(dropdownIngredientes);
    		
    		DropDownChoice<Temporadas> dropdownTemporadas = new DropDownChoice<Temporadas>("temporada",new PropertyModel<Temporadas>(items,"temporada"), negocio.getTemporadas(),new ChoiceRenderer<Temporadas>("temporada","idTemporada")){
   
				private static final long serialVersionUID = 1L;

				protected String getNullValidDisplayValue(){
    				return "Todas";
    			}
    		};
    		dropdownTemporadas.setNullValid(true);
    		add(dropdownTemporadas);
    		
    		DropDownChoice<Integer> dropdownCalificacion = new DropDownChoice<Integer>("calificaciones",new PropertyModel<Integer>(items, "calificacion"), negocio.getCalificaciones()){
    
				private static final long serialVersionUID = 1L;

				protected String getNullValidDisplayValue() {
    				return "Todas";
    			}
    		};
    		dropdownCalificacion.setNullValid(true);
    		add(dropdownCalificacion);
    		
    		DropDownChoice<GruposAlimenticios> dropdownGruposAlimenticios = new DropDownChoice<GruposAlimenticios>("grupoAlim",new PropertyModel<GruposAlimenticios>(items,"grupoAlimenticio"), negocio.getGrpAlim() ,new ChoiceRenderer<GruposAlimenticios>("grupoAlim","idGrupoAlim")){

				private static final long serialVersionUID = 1L;

				protected String getNullValidDisplayValue(){
    				return "Todos";
    			}
    		};
    		dropdownGruposAlimenticios.setNullValid(true);
        	add(dropdownGruposAlimenticios);
        	
        	NumberTextField<Integer> caloriasMin = new NumberTextField<Integer>("caloriasMin", new PropertyModel<Integer>(items, "caloriasMin"), Integer.class);
        	caloriasMin.setRequired(true);
        	add(caloriasMin);
        	NumberTextField<Integer> caloriasMax = new NumberTextField<Integer>("caloriasMax", new PropertyModel<Integer>(items, "caloriasMax"), Integer.class);
        	caloriasMax.setRequired(true);
        	add(caloriasMax);
    		
        	add(new Button("botonBuscar") {

				private static final long serialVersionUID = 1L;

				public void onSubmit() {
    		Fragment fragment = new  FragmentoRecetasBuscadas ("areaRecetas", "listaRecetas", frmBuscarReceta, items);
    		frmBuscarReceta.add(fragment);
        		}
        	});
			
			add(new EmptyPanel("areaRecetas"));
			
		}
	}
	
	private class FragmentoRecetasBuscadas extends Fragment {

		private static final long serialVersionUID = 1L;

		public FragmentoRecetasBuscadas(String id, String markupId, MarkupContainer markupPorvider, itemsABuscar queBuscar ) {
        	
        	super(id, markupId, markupPorvider);
        	
        	ArrayList<Receta> recetas;
			
			recetas= negocio.buscarPorFiltros(getUsuarioActual(), queBuscar);
			markupPorvider.remove(id);
	        	
	        add(new Label("nombreGrilla"," Resultados"));
	        add(new ListaDeRecetas("listaRecetas", recetas, getUsuarioActual(), pagina)); 
        }
	}
}
