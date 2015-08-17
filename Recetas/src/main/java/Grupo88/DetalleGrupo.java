package Grupo88;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.prefs.BackingStoreException;

import javax.swing.JOptionPane;

import master.MasterPage;
import master.RegisteredPage;
import objetosWicket.SesionUsuario;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.PopupSettings;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;
import org.eclipse.jetty.server.Server;
import org.apache.wicket.Session;

import Database.Browser;
import Grupo88.Inicio.FrmInicio;
import Grupo88.Login.FrmLogin;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Grupo;
import ObjetosDB.Pasos;
import ObjetosDB.RecetaU;
import ObjetosDB.Usuario;

public class DetalleGrupo extends RegisteredPage {

	// private TextField<String> txtUsuario;
	// private PasswordTextField txtPassword;
	//
	private FrmDetalleGrupo frmDetalleGrupo;
	private StringValue idGrupo;
	private Grupo grupo;
	
	
	public DetalleGrupo(final PageParameters parameters){
		super();
		//getMenuPanel().setVisible(false);
		if(parameters.getNamedKeys().contains("idGrupo")){
			idGrupo = parameters.get("idGrupo");
		
		grupo = Browser.obtenerGrupo(idGrupo.toInt());
		add(new FrmDetalleGrupo("frmDetalleGrupo"));
		
	}	
	}
	
	
	private class FrmDetalleGrupo extends Form{
		public FrmDetalleGrupo(String id) {
			super(id);
			
			add(new Label("nombreGrupo", grupo.getNombre()));
			add(new ListaDeRecetas("listaRecetas",grupo.obtenerRecetas(),getUsuarioActual()));
			
			RepeatingView usuariosGrupo = new RepeatingView("listaUsuarios");
			
			for (Usuario usuario : grupo.obtenerUsuarios()){
				
				AbstractItem item = new AbstractItem(usuariosGrupo.newChildId());
				
				item.add(new Label("unUsuario",usuario.getUsername()));
				
				usuariosGrupo.add(item);
			}
			
			add(usuariosGrupo);
		}
	
	}
}