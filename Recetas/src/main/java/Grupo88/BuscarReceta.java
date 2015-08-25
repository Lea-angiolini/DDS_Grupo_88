package Grupo88;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import master.MasterPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import Database.Browser;
import ObjetosDB.Dificultades;
import ObjetosDB.GruposAlimenticios;
import ObjetosDB.Ingredientes;
import ObjetosDB.Receta;
import ObjetosDB.Temporadas;
import ObjetosDB.Usuario;
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
    		
    		add(new DropDownChoice<Dificultades>("dificultad",new PropertyModel<Dificultades>(items,"dificultad"), dificultades ,new ChoiceRenderer("dificultad","idDificultad")));
    		add(new DropDownChoice<Temporadas>("temporada",new PropertyModel<Temporadas>(items,"temporada"), temporadas,new ChoiceRenderer("temporada","idTemporada")));
        	add(new DropDownChoice<Ingredientes>("ingrediente",new PropertyModel<Ingredientes>(items,"ingredientePrincipal"), ingredientes,new ChoiceRenderer("ingrediente","idIngrediente")));
        	add(new DropDownChoice<GruposAlimenticios>("grupoAlim",new PropertyModel<GruposAlimenticios>(items,"grupoAlimenticio"), grpAlim ,new ChoiceRenderer("grupoAlim","idGrupoAlim")));
        	add(new DropDownChoice<Integer>("calificaciones",new PropertyModel<Integer>(items, "calificacion"), Arrays.asList(1,2,3,4,5)));
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
        	
        	ArrayList<Receta> recetas = Browser.cargarRecetasBuscadas(queBuscar);	
    		
        	markupPorvider.remove(id);
        	
        	add(new Label("nombreGrilla"," Resultados"));
        	add(new ListaDeRecetas("listaRecetas", recetas, getUsuarioActual()));
            
        }
	}
}
