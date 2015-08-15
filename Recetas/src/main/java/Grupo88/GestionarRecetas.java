package Grupo88;

import java.util.prefs.BackingStoreException;

import master.MasterPage;
import master.RegisteredPage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.jetty.server.Server;
import org.apache.wicket.Session;

import Grupo88.Login.FrmLogin;

public class GestionarRecetas extends RegisteredPage {	
	
	private FrmGestionarRecetas frmGestionarRecetas;
	
	public GestionarRecetas(){
		super();
		getMenuPanel().setVisible(false);
		
		add(frmGestionarRecetas = new FrmGestionarRecetas("FrmGestionarRecetas"));
		
		frmGestionarRecetas.add(new Link("agregarReceta"){
			
			public void onClick(){
			setResponsePage(AgregarReceta.class);
			}
		});
		
		frmGestionarRecetas.add(new Link("volver"){
			
			public void onClick(){
			setResponsePage(Inicio.class);
			}
		});
		
	}
	
	public class FrmGestionarRecetas extends Form {
		
		
		public FrmGestionarRecetas(String id) {
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