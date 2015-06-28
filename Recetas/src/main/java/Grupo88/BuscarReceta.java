package Grupo88;

import java.util.ArrayList;
import java.util.List;

import master.MasterPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import Database.Browser;
import Grupo88.Inicio.FragmentoRecetasUsuario;
import ObjetosDB.Recetas;
import ObjetosDB.itemsABuscar;
import ObjetosDB.Recetas.Receta;

public class BuscarReceta extends MasterPage {

	private FrmBuscarReceta frmBuscarReceta;
	
	public BuscarReceta(){
		super();
		getMenuPanel().setVisible(false);
		
		add(frmBuscarReceta = new FrmBuscarReceta("FrmBuscarReceta"));
		
		frmBuscarReceta.add(new Link("volver"){
			
			public void onClick(){
			setResponsePage(Inicio.class);
			}
		});
		
		frmBuscarReceta.add(new Link("buscarReceta"){
			
			public void onClick(){
			setResponsePage(BuscarReceta.class);
			}
		});
		
		/*frmBuscarReceta.add(new Link("seleccionarReceta"){
			
			public void onClick(){
			setResponsePage(DetalleDeReceta.class);
			}
		});*/
	}
	
	public class FrmBuscarReceta extends Form {
		
		private DropDownChoice temporada;
		private String calorias;
		private DropDownChoice grupoAlimenticio;
		private DropDownChoice ingredientePrincipal;
		private DropDownChoice dificultad;
		private final IModel modelTemporadas = new Model<String>("");
		private final IModel modelGrupoAlim = new Model<String>("");
		private final IModel modelIngredientes = new Model<String>("");
		private final IModel modelDificultad = new Model<String>("");
		private Recetas recetas = new Recetas();
		private itemsABuscar abuscar = new itemsABuscar();
		
		public FrmBuscarReceta(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));
			
			add(new DropDownChoice("temporada",modelTemporadas,Browser.listaTemporadas()));
			add(new NumberTextField("calorias"));
			add(new DropDownChoice("grupoAlimenticio",modelGrupoAlim,Browser.listaGruposAlim()));
			add(new DropDownChoice("ingredientePrincipal",modelIngredientes,Browser.listaIngredientes()));
			add(new DropDownChoice("dificultad",modelDificultad,Browser.listaDificultades()));
			
		}
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
			super.onSubmit();
			
			/*abuscar.setTemporada(modelTemporadas.getObject().toString());
			abuscar.setDificultad(modelDificultad.getObject().toString());
			abuscar.setGrupoAlimenticio(modelGrupoAlim.getObject().toString());
			abuscar.setCaloriasMax(Integer.parseInt(calorias));
			abuscar.setIngredientePrincipal(modelIngredientes.getObject().toString());
			
			recetas = Browser.cargarRecetasBuscadas(abuscar);*/
			
			//Fragment fragment = new  FragmentoRecetasUsuario ("contentArea", "listaRecetas", frmInicio, "jorge");
			
			//frmBuscarReceta.add();
			
			
		}
		
		@SuppressWarnings("unchecked")
		private DataView generarTabla(Recetas recetas){
		
			return (new DataView("tablaPopulares",  new ListDataProvider(recetas.ObtenerColeccionRecetas())){
				@Override
				protected void populateItem(Item item) {
					// TODO Auto-generated method stub
					
					Link bton = new Link("bt"){
						@Override
						public void onClick() {
							// TODO Auto-generated method stub
							setResponsePage(DetalleDeReceta.class);
						}
						
					};
					
					final Receta recetas= (Receta) item.getModelObject();
					//item.addOrReplace(new Label("indice",item.getIndex()));
					
					bton.addOrReplace(new Label("campo1",recetas.getNombre()));
					bton.addOrReplace(new Label("campo2",recetas.getCreador()));
					bton.addOrReplace(new Label("campo3",recetas.getDificultad()));
					
					item.add(bton);
					
				}
			});
		}
		
	}
}
