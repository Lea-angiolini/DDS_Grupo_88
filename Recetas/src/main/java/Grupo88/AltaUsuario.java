package Grupo88;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;

import javax.jws.WebParam.Mode;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import master.MasterPage;
import objetosWicket.ModelUsuario;

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
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
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
import ObjetosDB.Recetas;
import ObjetosDB.Usuario;

public class AltaUsuario extends MasterPage {	
	
	private FrmAltaUsuario frmAltaUsuario;
	public Object estados;
	
	public AltaUsuario(){
		super();
		getMenuPanel().setVisible(false);
		
		add(frmAltaUsuario = new FrmAltaUsuario("FrmAltaUsuario"));
		
		frmAltaUsuario.add(new Link("cancelar"){
			
			@Override
			public void onClick() {
			
				setResponsePage(Login.class);
				
			}
		});	
	}
	
	public class FrmAltaUsuario extends Form {

		private Usuario usuario = new Usuario();
		private final ArrayList<estadoCondPreex> estados;
		
		@SuppressWarnings("unchecked")
		public FrmAltaUsuario(String id) {
			super(id);			
			//setDefaultModel(new CompoundPropertyModel(this));
			PasswordTextField password = new PasswordTextField("password", new PropertyModel<String>(usuario, "password"));
			PasswordTextField repPassword = new PasswordTextField("repPassword", Model.of("")); 
			
			
			add(new TextField("username", new PropertyModel<String>(usuario, "username")));
			add(password);
			add(repPassword);
			add(new EqualPasswordInputValidator(password, repPassword));
			add(new EmailTextField("email", new PropertyModel<String>(usuario, "email")).add(EmailAddressValidator.getInstance()));
			add(new TextField("nombre", new PropertyModel<String>(usuario, "nombre")));
			add(new TextField("apellido", new PropertyModel<String>(usuario, "apellido")));
			add(new DropDownChoice<Character>("sexo", new PropertyModel<Character>(usuario, "sexo"), Arrays.asList('M', 'F')));
			add(new TextField<String>("fechaNac", new PropertyModel<String>(usuario, "fechaNacimiento")));
			add(new NumberTextField("altura", new PropertyModel<Integer>(usuario, "altura"), Integer.class));
			add(new DropDownChoice<String>("complexion", new PropertyModel<String>(usuario, "complexion"), Browser.listaComplexiones()));
			
			RepeatingView condiciones = new RepeatingView("grupoCheckBox");
			ArrayList<String> condPrex = Browser.listaCondPreexistentes();
			estados = new ArrayList<estadoCondPreex>();
			
			for (int i = 0 ; i < condPrex.size(); i++) {
				
				AbstractItem item = new AbstractItem(condiciones.newChildId());
				
				estadoCondPreex actual = new estadoCondPreex(condPrex.get(i),new Model<Boolean>(false));
				estados.add(actual);
				
				item.add(new Label("textoCheckBox", actual.cond));
				item.add(new CheckBox("CheckBox", actual.modelCond));
				condiciones.add(item);
				
			}
			add(condiciones);	
			
		    add(new DropDownChoice<String>("dieta", new PropertyModel<String>(usuario, "dieta"), Browser.listaDietas()));
		    add(new DropDownChoice("rutina", new PropertyModel<String>(usuario, "rutina"),Browser.listaRutinas()));
		    add(new EmptyPanel("lblError"));
		   
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			addOrReplace(new Label("lblError",this.cargarDatosUsuario()));
		}
		
		private String cargarDatosUsuario(){
			
			for (estadoCondPreex estado : estados) {
				if(estado.modelCond.getObject())
				{
					usuario.setCondicion(estado.cond);
				}
			}

			ModelUsuario mUsuario = new ModelUsuario(usuario);
			return mUsuario.save(usuario);			
			
		}
	}
	
	private class estadoCondPreex{
		estadoCondPreex(String cond, IModel<Boolean> modelCond){
			this.cond = cond;
			this.modelCond = modelCond;
		}
		
		public String cond;
		public IModel<Boolean> modelCond;

		
	}
}