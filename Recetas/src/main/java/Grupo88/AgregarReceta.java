package Grupo88;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;

import master.MasterPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
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
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.jetty.server.Server;
import org.omg.CORBA.NVList;
import org.apache.wicket.Session;

import Grupo88.AltaUsuario.FrmAltaUsuario;
import Grupo88.Login.FrmLogin;
import ObjetosDB.RecetaU;
import ObjetosDB.Usuario;
import ObjetosDB.Recetas.Receta;

public class AgregarReceta extends MasterPage {
   
	private SesionUsuario sesion = (SesionUsuario)getSession();
	private Usuario user = sesion.getUsuario().getObject();
	private Fragmento frmAgregarReceta;
	private final RecetaU nuevareceta = new RecetaU(-1, "", "", "", "", "", 0);
	
	public AgregarReceta(){
		super();
		//getMenuPanel().setVisible(false);
		
		add(frmAgregarReceta = new Fragmento("areaForms","fragmentoInicial",this,new FrmDatosReceta("frmDatosBasicos")));
	}
	
	protected AgregarReceta pagina(){
		return this;
	}
	
	public class Fragmento extends Fragment{

		public Fragmento(String id, String markupId, MarkupContainer markupProvider, Form form) {
			super(id, markupId, markupProvider);
			// TODO Auto-generated constructor stub
			add(form);
		}
		
	}
	
	
	
	public class FrmDatosReceta extends Form {
		Model algo = new Model("");
		
		public FrmDatosReceta(String id) {
			super(id);
			// TODO Auto-generated constructor stub
			
		final TextField<String> nombre;
		add(nombre = new TextField<String>("nombreReceta", new PropertyModel<String>(nuevareceta, "nombre")));
		add(new TextArea<String>("descripcion", algo));
		}
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		pagina().addOrReplace(new Fragmento("areaForms","fragmentoPaso",pagina(), new FrmPaso("frmPasos")));
		
		
		}
	}
	
	public class FrmPaso extends Form {

		public FrmPaso(String id) {
			super(id);
			// TODO Auto-generated constructor stub
			
	
		}
		
		
		@Override
		protected void onSubmit() {
		// Va a conectarse con BD y comprobar las validaciones
		super.onSubmit();
		JOptionPane.showMessageDialog(null, nuevareceta.getNombre());
		//JOptionPane.showMessageDialog(null, algo.getObject());	
		}
	}
}