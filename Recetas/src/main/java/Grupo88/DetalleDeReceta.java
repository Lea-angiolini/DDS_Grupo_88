package Grupo88;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;

import master.MasterPage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.jetty.server.Server;
import org.apache.wicket.Session;

import Grupo88.AltaUsuario.FrmAltaUsuario;
import Grupo88.Inicio.FrmInicio;
import Grupo88.Login.FrmLogin;

public class DetalleDeReceta extends MasterPage {

//	private TextField<String> txtUsuario;
//	private PasswordTextField txtPassword;
//	
	private FrmDetalleDeReceta frmDetalleDeReceta;
	
	public DetalleDeReceta(){
		super();
		getMenuPanel().setVisible(false);
		
		add(frmDetalleDeReceta = new FrmDetalleDeReceta("FrmDetalleDeReceta"));
		
		frmDetalleDeReceta.add(new Link("confirmar"){
			
			public void onClick() {
				
				setResponsePage(Login.class);
			}
		});
		
		frmDetalleDeReceta.add(new Link("compartir"){
			
			public void onClick() {
				
				setResponsePage(Login.class);
			}
		});
		
		
	}
	
	public class FrmDetalleDeReceta extends Form {
		

		public FrmDetalleDeReceta(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));
			
		}
		
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		}
	}
}