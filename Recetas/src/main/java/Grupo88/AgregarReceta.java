package Grupo88;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;

import master.MasterPage;
import master.RegisteredPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.jetty.server.Server;
import org.omg.CORBA.NVList;
import org.apache.wicket.Session;

import Database.Browser;
import Grupo88.Login.FrmLogin;
import ObjetosDB.Condimentos;
import ObjetosDB.Dificultades;
import ObjetosDB.Ingredientes;
import ObjetosDB.Pasos;
import ObjetosDB.RecetaU;
import ObjetosDB.Temporadas;
import ObjetosDB.Usuario;
import ObjetosDB.Receta;

public class AgregarReceta extends RegisteredPage {
   
	private SesionUsuario sesion = (SesionUsuario)getSession();
	private Usuario user = sesion.getUsuario().getObject();
	private final RecetaU nuevareceta = new RecetaU(-1, "", user.getUsername(), null, null, null, 0);
	private List<Fragmento> fragmentos = new ArrayList<Fragmento>();
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
		
		public FrmDatosReceta(String id) {
			super(id);
			// TODO Auto-generated constructor stub
		//final TextField<String> nombre;
		add(new TextField<String>("nombreReceta", new PropertyModel<String>(nuevareceta, "nombre")));
		add(new TextArea<String>("descripcion", new PropertyModel<String>(nuevareceta, "detalle")));
		add(new DropDownChoice<Temporadas>("temporada", new PropertyModel<Temporadas>(nuevareceta, "temporada"), Browser.listaTemporadas(), new ChoiceRenderer<Temporadas>("temporada", "idTemporada")));
		add(new DropDownChoice<Dificultades>("dificultad", new PropertyModel<Dificultades>(nuevareceta, "dificultad"), Browser.listaDificultades(), new ChoiceRenderer<Dificultades>("dificultad", "idDificultad")));
		add(new DropDownChoice<Ingredientes>("ingPrinc", new PropertyModel<Ingredientes>(nuevareceta, "ingredientePrincipal"), Browser.listaIngredientes(), new ChoiceRenderer<Ingredientes>("ingrediente", "idIngrediente")));
		
		add(new DropList<Ingredientes>("dropIngredientes",Browser.listaIngredientes()));
		add(new DropList<Condimentos>("dropCondimentos",Browser.listaCondimentos()));
		}
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		pagina().addOrReplace(fragmentos.get(1));
		
		
		}
	}
	
	public class FrmPaso extends Form {
		
		private int idFrmPaso;
		public FrmPaso(String id, int idPaso) {
			super(id);
			// TODO Auto-generated constructor stub
			idFrmPaso = idPaso;
			
			add(new Label("numPaso",idPaso));
			add(new TextArea<String>("paso", new PropertyModel<String>(nuevareceta.getPasos().get(idPaso-1), "descripcionPaso")));
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