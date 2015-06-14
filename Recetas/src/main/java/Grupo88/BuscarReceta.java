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
import Grupo88.Login.FrmLogin;

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
		
		frmBuscarReceta.add(new Link("seleccionarReceta"){
			
			public void onClick(){
			setResponsePage(DetalleDeReceta.class);
			}
		});
	}
	
	public class FrmBuscarReceta extends Form {
		
		private DropDownChoice temporada;
		private String calorias;
		private DropDownChoice grupoAlimenticio;
		private DropDownChoice ingredientePrincipal;
		private DropDownChoice dificultad;
		
		public FrmBuscarReceta(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));
			
			add(new DropDownChoice("temporada"));
			add(new NumberTextField("calorias"));
			add(new DropDownChoice("grupoAlimenticio"));
			add(new DropDownChoice("ingredientePrincipal"));
			add(new DropDownChoice("dificultad"));
			
		}
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		}
	}
}