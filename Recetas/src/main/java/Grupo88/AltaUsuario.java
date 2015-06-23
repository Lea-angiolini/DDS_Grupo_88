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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
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
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
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
		
		private String username;
		private String password;
		private String repPassword;
		private String email;
		private String nombre;
		private String apellido;
		private final IModel modelSexo = new Model<String>("");
		private int altura;
		private final IModel<String> modelComplexion = new Model<String>("");
		private final IModel modelCondPreex= new Model<String>("");
		private final ArrayList<estadoCondPreex> estados = new ArrayList<estadoCondPreex>();
		private final IModel modelDietas = new Model<String>("");
		private final IModel modelRutinas = new Model<String>("");
		
		@SuppressWarnings("unchecked")
		public FrmAltaUsuario(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));

			add(new TextField("username"));
			//add(new PasswordTextField("password"));
			//add(new PasswordTextField("repPassword"));
			add(new EmailTextField("email"));
			add(new TextField("nombre"));
			add(new TextField("apellido"));
			add(new DropDownChoice("sexo",modelSexo,Arrays.asList("Masculino", "Femenino")));
			add(new NumberTextField("altura"));
			add(new DropDownChoice("complexion",this.modelComplexion, Browser.listaComplexiones()));
			add(loopCheckBox());
		    add(new DropDownChoice("dieta",modelDietas,Browser.listaDietas()));
		    add(new DropDownChoice("rutina",modelRutinas,Browser.listaRutinas()));
			
		}
		
		@Override
		protected void onSubmit() {
		super.onSubmit();
		frmAltaUsuario.cargarDatosUsuario();
		}
		
		private Usuario cargarDatosUsuario(){
			Usuario usuario = new Usuario();
			
			usuario.setUsername(username);
			usuario.setPassword(password);
			usuario.setEmail(email);
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setAltura(altura);
			usuario.setSexo((modelSexo.getObject().toString() == "Masculino") ? 'M' : 'F' );
			JOptionPane.showMessageDialog(null, ""+modelSexo.getObject().toString()+"    "+usuario.getSexo());
			return usuario;
			
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