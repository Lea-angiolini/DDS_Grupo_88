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
import org.apache.wicket.markup.html.form.CheckBox;
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
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.eclipse.jetty.server.Server;
import org.apache.wicket.Session;

import Database.Browser;
import Grupo88.AltaUsuario.FrmAltaUsuario;
import Grupo88.Inicio.FrmInicio;
import Grupo88.Login.FrmLogin;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.RecetaU;

public class DetalleDeReceta extends MasterPage {

//	private TextField<String> txtUsuario;
//	private PasswordTextField txtPassword;
//	
	private FrmDetalleDeReceta frmDetalleDeReceta;
	
	public DetalleDeReceta(final PageParameters parameters){
		super();
		getMenuPanel().setVisible(false);
		
		StringValue idReceta;
		RecetaU receta;
		
		if(parameters.getNamedKeys().contains("idReceta")){
			idReceta = parameters.get("idReceta");
			receta = Browser.cargarReceta(idReceta.toInt());
		
		
		
		
		add(frmDetalleDeReceta = new FrmDetalleDeReceta("FrmDetalleDeReceta"));
		
		frmDetalleDeReceta.add(new Label("Nombre",receta.getNombre()));
		frmDetalleDeReceta.add(new Label("dif",receta.getDificultad()));
		frmDetalleDeReceta.add(new Label("tem",receta.getTemporada()));
		
		RepeatingView ingredientes = new RepeatingView("listaIngredientes");
		
		for (String ingrediente : receta.getIngredientes()) {
			
			AbstractItem item = new AbstractItem(ingredientes.newChildId());
					
			item.add(new Label("unIngrediente", ingrediente));
			ingredientes.add(item);
			
		}
		
		frmDetalleDeReceta.add(ingredientes);
		
		RepeatingView condimentos = new RepeatingView("listaCondimentos");
		
		for (String condimento : receta.getCondimentos()) {
			
			AbstractItem item = new AbstractItem(condimentos.newChildId());
					
			item.add(new Label("unCondimento", condimento));
			condimentos.add(item);
			
		}
		
		frmDetalleDeReceta.add(condimentos);
		
		RepeatingView pasos = new RepeatingView("pasos");
		
		int i = 0;
		for (String paso : receta.getPasos()) {
			
			AbstractItem item = new AbstractItem(pasos.newChildId());
			i++;
			item.add(new Label("nro","Paso "+i+":"));		
			item.add(new Label("paso", paso));
			pasos.add(item);
			
		}
		
		frmDetalleDeReceta.add(pasos);
		
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