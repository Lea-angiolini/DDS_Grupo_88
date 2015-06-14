package Grupo88;

import java.awt.Checkbox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;

import master.MasterPage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.jetty.server.Server;
import org.apache.wicket.Session;

import Grupo88.Login.FrmLogin;

public class GestionarPerfil extends MasterPage {	
	
	private FrmGestionarPerfil frmGestionarPerfil;
	
	public GestionarPerfil(){
		super();
		getMenuPanel().setVisible(false);
		
		add(frmGestionarPerfil = new FrmGestionarPerfil("FrmGestionarPerfil"));
		
		frmGestionarPerfil.add(new Link("cancelar"){
			
			@Override
			public void onClick() {
			
				setResponsePage(Inicio.class);
				
			}
		});
	}
	
	public class FrmGestionarPerfil extends Form {
		
		

		private String nombre;
		private String edad;
		private String altura;
		private DropDownChoice complexion;
		private DropDownChoice dieta;
		private List<String> preferenciasAlimenticias = Arrays.asList(new String[] {
				"carne", "pollo", "mariscos", "vegetales"});
		private ArrayList<String> preferenciasAlimenticiasSeleccionadas = new ArrayList<String>();
		private List<String> condicionesPreexistentes = Arrays.asList(new String[] {
				"diabetes", "hipertension", "intolerancia a lacteos", "celiaquia"});
		private ArrayList<String> condicionesPreexistentesSeleccionadas = new ArrayList<String>();

		public FrmGestionarPerfil(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));
		

			add(new TextField("nombre"));
			add(new NumberTextField("edad"));
			add(new NumberTextField("altura"));
			add(new DropDownChoice("complexion"));
			add(new DropDownChoice("dieta"));
			add(new RadioGroup("sexo"));
		    add(new CheckBoxMultipleChoice<String>("preferenciasAlimenticias", new Model(preferenciasAlimenticiasSeleccionadas),preferenciasAlimenticias));
		    add(new CheckBoxMultipleChoice<String>("condicionesPreexistentes", new Model(condicionesPreexistentesSeleccionadas),condicionesPreexistentes));

		}
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		}
	}
}