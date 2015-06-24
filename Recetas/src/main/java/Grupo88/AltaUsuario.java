package Grupo88;

import java.lang.reflect.Array;
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
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
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
		
		/*frmAltaUsuario.add(new Link("cancelar2"){
			
			@Override
			public void onClick() {
			
				setResponsePage(Login.class);
				
			}
		});*/
		
	}
	
	public class FrmAltaUsuario extends Form {
		
		/*private String username;
		private String password;
		private String repPassword;
		private String email;
		private String nombre;
		private String apellido;
		private final IModel modelSexo = new Model<String>("");
		private int altura;
		private final IModel<String> modelComplexion = new Model<String>("");
		private final IModel modelCondPreex= new Model<String>("");*/
		private final ArrayList<estadoCondPreex> estados = new ArrayList<estadoCondPreex>();
		/*private final IModel modelDietas = new Model<String>("");
		private final IModel modelRutinas = new Model<String>("");*/
		private Usuario usuario = new Usuario();
		
		
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
			add(new NumberTextField("altura", new PropertyModel<Integer>(usuario, "altura"), Integer.class));
			add(new DropDownChoice<String>("complexion", new PropertyModel<String>(usuario, "complexion"), Browser.listaComplexiones()));
			
			
			//TODO: Sacar el fragment y usar este iterador
			//RepeatingView condiciones = new RepeatingView("condiciones"); 
			//condiciones.add(new CheckBox(condiciones.newChildId(), );
			
			add(loopCheckBox());
			
			
			
			
		    add(new DropDownChoice<String>("dieta", new PropertyModel<String>(usuario, "dieta"), Browser.listaDietas()));
		    add(new DropDownChoice("rutina", new PropertyModel<String>(usuario, "rutina"),Browser.listaRutinas()));
		    add(new EmptyPanel("lblError"));
		    
			
		}
		
		@Override
		protected void onSubmit() {
		super.onSubmit();
			super.onSubmit();
			this.cargarDatosUsuario();
			addOrReplace(new Label("lblError",Browser.registrarUsuario(usuario)));
		}
		
		private void cargarDatosUsuario(){
			ModelUsuario mUsuario = new ModelUsuario(usuario);
			mUsuario.save(usuario);			
			
		}
	}
	
	private class FragmentoCheckBox extends Fragment {
        public FragmentoCheckBox(String id, String markupId,MarkupContainer markupPorvider, String condPreex) {
        	
        	super(id, markupId, markupPorvider);

        	estadoCondPreex estadoActual = new estadoCondPreex(condPreex,Model.of(Boolean.FALSE));
        	frmAltaUsuario.estados.add(estadoActual);
        	
        	CheckBox condPreexistentes = new CheckBox("condPreexistentes", estadoActual.modelCond);
			add(condPreexistentes);
            
			add(new Label("labelCheck",condPreex));
        }

	}
	
	private Loop loopCheckBox(){
		
		final ArrayList<String> condPreex = Browser.listaCondPreexistentes();
		
		@SuppressWarnings("serial")
		Loop loop = new Loop("sup", condPreex.size()) {
	           protected void populateItem(LoopItem item) {
	                
	        	   FragmentoCheckBox fragmento = new FragmentoCheckBox("listaCheckBox", "CheckBoxFragment", frmAltaUsuario, condPreex.get(item.getIndex()));
	                item.add(fragmento);
	            }
	        };
	    return loop;
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