package Grupo88;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;

import master.MasterPage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
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
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.eclipse.jetty.server.Server;
import org.apache.wicket.Session;

import Database.Browser;
import Grupo88.AltaUsuario.FrmAltaUsuario;
import Grupo88.Login.FrmLogin;
import ObjetosDB.Recetas;
import ObjetosDB.Recetas.Receta;

public class Inicio extends MasterPage {

	private FrmInicio frmInicio;
	
	@SuppressWarnings("unchecked")
	public Inicio(){
		super();
		getMenuPanel().setVisible(false);
		
		add(frmInicio = new FrmInicio("FrmInicio"));
		
		frmInicio.add(new Link("salir"){
			
			public void onClick() {
			setResponsePage(Login.class);
				
			}
		});
		
		frmInicio.add(new Link("gestionarRecetas"){
			
			public void onClick(){
			setResponsePage(GestionarRecetas.class);
			}
		});
		
		frmInicio.add(new Link("gestionarPerfil"){
			
			public void onClick(){
			//setResponsePage(GestionarPerfil.class);
			}
		});
		
		
		frmInicio.add(new Link("buscarReceta"){
			
			public void onClick() {
				
				setResponsePage(BuscarReceta.class);
			}
		});
		
		
		
		
		Recetas recetas = Browser.cargarRecetasPopulares();		
		
		frmInicio.add(new DataView("tablaPopulares",  new ListDataProvider(recetas.ObtenerColeccionRecetas())){
			@Override
			protected void populateItem(Item item) {
				// TODO Auto-generated method stub
				
				Link bton = new Link("bt"){
					@Override
					public void onClick() {
						// TODO Auto-generated method stub
						setResponsePage(DetalleDeReceta.class);
					}
					
				};
				
				final Receta recetas= (Receta) item.getModelObject();
				//item.addOrReplace(new Label("indice",item.getIndex()));
				
				bton.addOrReplace(new Label("campo1",recetas.getNombre()));
				bton.addOrReplace(new Label("campo2",recetas.getCreador()));
				bton.addOrReplace(new Label("campo3",recetas.getDificultad()));
				
				item.add(bton);
				
			}
		});
/*
		Button boton = new Button("bt1"){
			public void onSubmit() {
				
				setResponsePage(BuscarReceta.class);
			};
		};
		
		String span1;
		span1 = "sadmpasdmam,sdmam,sdsad";
		boton.add(new Label("span1",span1));
		frmInicio.add(boton);*/
				
		
	}
	
	public class FrmInicio extends Form {
		
		

		public FrmInicio(String id) {
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