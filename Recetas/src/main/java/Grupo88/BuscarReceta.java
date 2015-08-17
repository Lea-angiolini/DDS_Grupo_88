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
import Grupo88.Inicio.FragmentoBuscarRecetas;
import Grupo88.Inicio.FragmentoRecetasBuscadas;
import Grupo88.Inicio.FragmentoRecetasUsuario;
import ObjetosDB.Dificultades;
import ObjetosDB.GruposAlimenticios;
import ObjetosDB.Ingredientes;
import ObjetosDB.Recetas;
import ObjetosDB.Temporadas;
import ObjetosDB.Usuario;
import ObjetosDB.itemsABuscar;
import ObjetosDB.Recetas.Receta;

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
        	
    		add(new DropDownChoice<Dificultades>("dificultad",new PropertyModel<Dificultades>(items,"dificultad"), Browser.listaDificultades(),new ChoiceRenderer("dificultad","idDificultad")));
    		add(new DropDownChoice<Temporadas>("temporada",new PropertyModel<Temporadas>(items,"temporada"), Browser.listaTemporadas(),new ChoiceRenderer("temporada","idTemporada")));
        	add(new DropDownChoice<Ingredientes>("ingrediente",new PropertyModel<Ingredientes>(items,"ingredientePrincipal"), Browser.listaIngredientes(),new ChoiceRenderer("ingrediente","idIngrediente")));
        	add(new DropDownChoice<GruposAlimenticios>("grupoAlim",new PropertyModel<GruposAlimenticios>(items,"grupoAlimenticio"), Browser.listaGruposAlim(),new ChoiceRenderer("grupoAlim","idGrupoAlim")));
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
        	
        	Recetas recetas = Browser.cargarRecetasBuscadas(queBuscar);	
    		
        	markupPorvider.remove(id);
        	
        	add(new Label("nombreGrilla"," Resultados"));
        	add(new ListaDeRecetas("listaRecetas", recetas.ObtenerColeccionRecetas(), getUsuarioActual()));
            
        }
	}
}
