package Grupo88.BuscarReceta;

import java.util.ArrayList;
import java.util.Arrays;

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

import Database.Browser;
import Grupo88.Componentes.ListaDeRecetas;
import ObjetosDB.Calificacion;
import ObjetosDB.Dificultades;
import ObjetosDB.GruposAlimenticios;
import ObjetosDB.Ingredientes;
import ObjetosDB.RecetaU;
import ObjetosDB.Temporadas;
import ObjetosDB.itemsABuscar;

public class BuscarReceta extends MasterPage {
	
	private FrmBuscarReceta frmBuscarReceta;
	
	public BuscarReceta(){
		super();
		
		add(frmBuscarReceta = new FrmBuscarReceta("FrmBuscarReceta"));
		
		}
	
	private class FrmBuscarReceta extends Form {
		
		
		public FrmBuscarReceta(String id) {
			super(id);			
			
    		final itemsABuscar items = new itemsABuscar();
        	
    		ArrayList<Dificultades> dificultades = Browser.listaDificultades();
    		dificultades.add(0, new Dificultades(-1, "Todas"));
    		
    		ArrayList<Temporadas> temporadas = Browser.listaTemporadas();
    		temporadas.add(0, new Temporadas(-1, "Todas"));
    		
    		ArrayList<Ingredientes> ingredientes = Browser.listaIngredientes();
    		ingredientes.add(0, new Ingredientes(-1,"Todos",0,0));
    		
    		ArrayList<GruposAlimenticios> grpAlim = Browser.listaGruposAlim();
    		grpAlim.add(0, new GruposAlimenticios(-1, "Todos"));
    		
    		ArrayList<Calificacion> calificaciones = new ArrayList<Calificacion>();
    		calificaciones.add(new Calificacion("Todas", -1));
    		calificaciones.add(new Calificacion("Sin calificar", 0));
    		for(int i = 1; i<= 5;i++){
    			calificaciones.add(new Calificacion(String.valueOf(i), i));
    		}
    		
    		add(new DropDownChoice<Dificultades>("dificultad",new PropertyModel<Dificultades>(items,"dificultad"), dificultades ,new ChoiceRenderer("dificultad","idDificultad")));
    		add(new DropDownChoice<Temporadas>("temporada",new PropertyModel<Temporadas>(items,"temporada"), temporadas,new ChoiceRenderer("temporada","idTemporada")));
        	add(new DropDownChoice<Ingredientes>("ingrediente",new PropertyModel<Ingredientes>(items,"ingredientePrincipal"), ingredientes,new ChoiceRenderer("ingrediente","idIngrediente")));
        	add(new DropDownChoice<GruposAlimenticios>("grupoAlim",new PropertyModel<GruposAlimenticios>(items,"grupoAlimenticio"), grpAlim ,new ChoiceRenderer("grupoAlim","idGrupoAlim")));
        	add(new DropDownChoice<Calificacion>("calificaciones",new PropertyModel<Calificacion>(items, "calificacion"), calificaciones, new ChoiceRenderer("calificacion","valor")));
        	add(new NumberTextField<Integer>("caloriasMin", new PropertyModel<Integer>(items, "caloriasMin"), Integer.class));
        	add(new NumberTextField<Integer>("caloriasMax", new PropertyModel<Integer>(items, "caloriasMax"), Integer.class));
    		
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
        	
        	ArrayList<RecetaU> recetas = getUsuarioActual().filtrarRecetas(Browser.cargarRecetasBuscadas(queBuscar));	
    		
        	markupPorvider.remove(id);
        	
        	add(new Label("nombreGrilla"," Resultados"));
        	add(new ListaDeRecetas("listaRecetas", recetas, getUsuarioActual()));
            
        }
	}
}
