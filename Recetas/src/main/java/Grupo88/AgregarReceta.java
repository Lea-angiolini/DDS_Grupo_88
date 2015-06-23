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

public class AgregarReceta extends MasterPage {

	private FrmAgregarReceta frmAgregarReceta;
	
	public AgregarReceta(){
		super();
		getMenuPanel().setVisible(false);
		
		add(frmAgregarReceta = new FrmAgregarReceta("FrmAgregarReceta"));
		
		frmAgregarReceta.add(new Link("cancelar"){
			
			public void onClick(){
			setResponsePage(GestionarRecetas.class);
			}
		});
		
		
	}
	
	public class FrmAgregarReceta extends Form {
		
		private String nombreReceta;
		private String ingrediente;
		private String nuevoIngrediente;
		private String condimento;
		private String nuevoCondimento;
		private String calorias;
		private String tipoCondimento;
		private DropDownChoice temporada;
		private String caloriasTotales;
		private List<String> clasificacion = Arrays.asList(new String[] {
				"desayuno", "amuerzo", "merienda", "cena"});
		private ArrayList<String> clasificacionesSeleccionadas = new ArrayList<String>();
		private DropDownChoice grupoAlimenticio;

		public FrmAgregarReceta(String id) {
			super(id);			
			setDefaultModel(new CompoundPropertyModel(this));
			
			
		add(new TextField("nombreReceta"));
		add(new TextField("ingrediente"));
		add(new TextField("nuevoIngrediente"));
		add(new TextField("condimento"));
		add(new TextField("nuevoCondimento"));
		add(new NumberTextField("calorias"));
		add(new TextField("tipoCondimento"));
		add(new RadioGroup("dificultad"));
		add(new DropDownChoice("temporada"));
		add(new NumberTextField("caloriasTotales"));
		add(new CheckBoxMultipleChoice<String>("clasificacion", new Model(clasificacionesSeleccionadas),clasificacion));
		add(new RadioGroup("calificacion"));
		add(new DropDownChoice("grupoAlimenticio"));
		}
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		
		}
	}
}