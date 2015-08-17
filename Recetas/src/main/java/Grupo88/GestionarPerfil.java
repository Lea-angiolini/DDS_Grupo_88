package Grupo88;

import java.awt.FlowLayout;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;

import javax.jws.WebParam.Mode;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import master.MasterPage;
import master.RegisteredPage;
import objetosWicket.ModelUsuario;
import objetosWicket.SesionUsuario;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.eclipse.jetty.server.Server;
import org.apache.wicket.Session;

import Database.Browser;
import Grupo88.Login.FrmLogin;
import ObjetosDB.Complexiones;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Dietas;
import ObjetosDB.Recetas;
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
			add(new TextField<String>("password", new PropertyModel<String>(usuario, "password")));
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