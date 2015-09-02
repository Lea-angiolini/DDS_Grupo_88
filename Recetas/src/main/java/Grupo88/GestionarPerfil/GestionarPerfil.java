package Grupo88.GestionarPerfil;

import java.util.ArrayList;
import java.util.Arrays;

import master.RegisteredPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import Database.Browser;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Complexiones;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Dietas;
import ObjetosDB.Rutinas;
import ObjetosDB.Usuario;

public class GestionarPerfil extends RegisteredPage {	
	
	private FrmModifUsuario frmModifUsuario;
	public Object estados;
	
	public GestionarPerfil(){
		super();
		
		add(frmModifUsuario = new FrmModifUsuario("FrmModifUsuario"));
	}
	
	
	public class FrmModifUsuario extends Form {

		private Usuario usuario = getUsuarioActual();
		private final ArrayList<estadoCondPreex> estados;
		
		@SuppressWarnings("unchecked")
		public FrmModifUsuario(String id) {
			super(id);			
			//setDefaultModel(new CompoundPropertyModel(this));
		
			add(new EmailTextField("email", new PropertyModel<String>(usuario, "email")).add(EmailAddressValidator.getInstance()));
			add(new TextField<String>("nombre", new PropertyModel<String>(usuario, "nombre")));
			add(new TextField<String>("apellido", new PropertyModel<String>(usuario, "apellido")));
			
			add(new DropDownChoice<Character>("sexo", new PropertyModel<Character>(usuario, "sexo"), Arrays.asList('M', 'F')));
			add(new TextField<String>("fechaNac", new PropertyModel<String>(usuario, "fechaNacimiento")));
			add(new NumberTextField<Integer>("altura", new PropertyModel<Integer>(usuario, "altura"), Integer.class));
			add(new DropDownChoice<Complexiones>("complexion", new PropertyModel<Complexiones>(usuario, "complexion"), Browser.listaComplexiones(), new ChoiceRenderer<Complexiones>("complexion","idComplexion")));		
			
			RepeatingView condiciones = new RepeatingView("grupoCheckBox");
			ArrayList<CondicionesPreexistentes> listaCondPreexistentes = Browser.listaCondPreexistentes();
			estados = new ArrayList<estadoCondPreex>();
			
			for (CondicionesPreexistentes condPreex : listaCondPreexistentes) {
				
				AbstractItem item = new AbstractItem(condiciones.newChildId());
				
				estadoCondPreex actual = new estadoCondPreex(condPreex,new Model<Boolean>(false));
				estados.add(actual);
				
				item.add(new Label("textoCheckBox", actual.cond.getCondPreex()));
				CheckBox check = new CheckBox("CheckBox", actual.modelCond);
				check.setModelObject(usuario.tineCondPreex(condPreex));
				item.add(check);
				condiciones.add(item);
				
			}
			add(condiciones);	
			
		    add(new DropDownChoice<Dietas>("dieta", new PropertyModel<Dietas>(usuario, "dieta"), Browser.listaDietas(), new ChoiceRenderer<Dietas>("dieta","idDieta")));
		    add(new DropDownChoice<Rutinas>("rutina", new PropertyModel<Rutinas>(usuario, "rutina"),Browser.listaRutinas(), new ChoiceRenderer<Rutinas>("rutina","idRutina")));
		    add(new EmptyPanel("lblError"));
		    
		    add(new Link("cancelar"){
				
				@Override
				public void onClick() {
				
					setResponsePage(Inicio.class);
					
				}
			});	
		   
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			addOrReplace(new Label("lblError",usuario.modificarPerfil()));
			//setResponsePage(Inicio.class);
		}
	}
	
	private class estadoCondPreex{
		estadoCondPreex(CondicionesPreexistentes cond, IModel<Boolean> modelCond){
			this.cond = cond;
			this.modelCond = modelCond;
		}
		
		public CondicionesPreexistentes cond;
		public IModel<Boolean> modelCond;

		
	}
}
