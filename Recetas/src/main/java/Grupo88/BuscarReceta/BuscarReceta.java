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

import Database.DAODificultades;
import Database.DAOGruposAlimenticios;
import Database.DAOIngredientes;
import Database.DAORecetas;
import Database.DAOTemporadas;
import Database.DBExeption;
import Grupo88.Componentes.ListaDeRecetas;
import ObjetosDB.Calificacion;
import ObjetosDB.Dificultades;
import ObjetosDB.GruposAlimenticios;
import ObjetosDB.Ingredientes;
import ObjetosDB.Receta;
import ObjetosDB.Temporadas;
import ObjetosDB.itemsABuscar;

public class BuscarReceta extends MasterPage {
	
	private FrmBuscarReceta frmBuscarReceta;
	private DAODificultades daodificultades;
	private DAOTemporadas daotemporadas;
	private DAOIngredientes daoingredientes;
	private DAOGruposAlimenticios daogruposalimenticios;
	private DAORecetas daorecetas;
	private BuscarReceta pagina;
	
	public BuscarReceta(){
		super();
		
		daodificultades = new DAODificultades(getSessionBD());
		daotemporadas = new DAOTemporadas(getSessionBD());
		daoingredientes = new DAOIngredientes(getSessionBD());
		daogruposalimenticios = new DAOGruposAlimenticios(getSessionBD());
		daorecetas = new DAORecetas(getSessionBD());
		
		pagina = this;
		add(frmBuscarReceta = new FrmBuscarReceta("FrmBuscarReceta"));
		
		}
	
	private class FrmBuscarReceta extends Form {
		
		final itemsABuscar items = new itemsABuscar();
		private ArrayList<Dificultades> dificultades;
		private ArrayList<Temporadas> temporadas;
		private ArrayList<Ingredientes> ingredientes;
		private ArrayList<GruposAlimenticios> grpAlim;
		private ArrayList<Integer> calificaciones;
		
		public FrmBuscarReceta(String id) {
			super(id);			
			
			try {
				dificultades = (ArrayList<Dificultades>) daodificultades.findAll();
				
				temporadas = (ArrayList<Temporadas>) daotemporadas.findAll();
	    		
	    		ingredientes = (ArrayList<Ingredientes>) daoingredientes.findAll();
	    		
	    		grpAlim =  (ArrayList<GruposAlimenticios>) daogruposalimenticios.findAll();
	    		
	    		calificaciones = new ArrayList<Integer>();
	    		for(int i = 1; i<= 5;i++){
	    			calificaciones.add(i);
	    		}
				
			} catch (DBExeption e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			//
			DropDownChoice<Dificultades> dropdownDificultades = new DropDownChoice<Dificultades>("dificultad",new PropertyModel<Dificultades>(items,"dificultad"), dificultades ,new ChoiceRenderer("dificultad","idDificultad")){
    			@Override
    			protected String getNullValidDisplayValue() {
    				// TODO Auto-generated method stub
    				return "Todas";
    			}
    		};
    		dropdownDificultades.setNullValid(true);
    		add(dropdownDificultades);
    		
    		//
    		DropDownChoice<Ingredientes> dropdownIngredientes = new DropDownChoice<Ingredientes>("ingrediente",new PropertyModel<Ingredientes>(items,"ingredientePrincipal"), ingredientes,new ChoiceRenderer("ingrediente","idIngrediente")){
    			
    			protected String getNullValidDisplayValue() {
    				return "Todos";
    			}
    		};
    		dropdownIngredientes.setNullValid(true);
    		add(dropdownIngredientes);
    		//
    		DropDownChoice<Temporadas> dropdownTemporadas = new DropDownChoice<Temporadas>("temporada",new PropertyModel<Temporadas>(items,"temporada"), temporadas,new ChoiceRenderer("temporada","idTemporada")){
    			protected String getNullValidDisplayValue(){
    				return "Todas";
    			}
    		};
    		dropdownTemporadas.setNullValid(true);
    		add(dropdownTemporadas);
    		//
    		
    		DropDownChoice<Integer> dropdownCalificacion = new DropDownChoice<Integer>("calificaciones",new PropertyModel<Integer>(items, "calificacion"), calificaciones){
    			protected String getNullValidDisplayValue() {
    				return "Todas";
    			}
    		};
    		dropdownCalificacion.setNullValid(true);
    		add(dropdownCalificacion);
    		//
    		
    		DropDownChoice<GruposAlimenticios> dropdownGruposAlimenticios = new DropDownChoice<GruposAlimenticios>("grupoAlim",new PropertyModel<GruposAlimenticios>(items,"grupoAlimenticio"), grpAlim ,new ChoiceRenderer("grupoAlim","idGrupoAlim")){
    			protected String getNullValidDisplayValue(){
    				return "Todos";
    			}
    		};
    		dropdownGruposAlimenticios.setNullValid(true);
        	add(dropdownGruposAlimenticios);
        	//
        	
        	NumberTextField<Integer> caloriasMin = new NumberTextField<Integer>("caloriasMin", new PropertyModel<Integer>(items, "caloriasMin"), Integer.class);
        	caloriasMin.setRequired(true);
        	add(caloriasMin);
        	NumberTextField<Integer> caloriasMax = new NumberTextField<Integer>("caloriasMax", new PropertyModel<Integer>(items, "caloriasMax"), Integer.class);
        	caloriasMax.setRequired(true);
        	add(caloriasMax);
    		
        	add(new Button("botonBuscar") {
        		public void onSubmit() {
    		Fragment fragment = new  FragmentoRecetasBuscadas ("areaRecetas", "listaRecetas", frmBuscarReceta, items);
    		frmBuscarReceta.add(fragment);
        		}
        	});
			
			add(new EmptyPanel("areaRecetas"));
			
		}
	}
	
	private class FragmentoRecetasBuscadas extends Fragment {
        public FragmentoRecetasBuscadas(String id, String markupId, MarkupContainer markupPorvider, itemsABuscar queBuscar ) {
        	
        	super(id, markupId, markupPorvider);
        	
        	ArrayList<Receta> recetas;
			try {
				recetas = getUsuarioActual().filtrarRecetas((ArrayList<Receta>)daorecetas.buscarReceta(queBuscar));
				markupPorvider.remove(id);
	        	
	        	add(new Label("nombreGrilla"," Resultados"));
	        	add(new ListaDeRecetas("listaRecetas", recetas, getUsuarioActual(), pagina));
			} catch (DBExeption e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				setResponsePage(new ErrorPage("upss"));
			}	
    		
        	
            
        }
	}
}
