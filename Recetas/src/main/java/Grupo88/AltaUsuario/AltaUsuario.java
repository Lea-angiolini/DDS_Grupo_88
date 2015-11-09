package Grupo88.AltaUsuario;

import java.util.ArrayList;
import java.util.Arrays;

import master.ErrorPage;
import master.MasterPage;
import objetosWicket.ModelUsuario;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import Database.DAOComplexiones;
import Database.DAOCondicionesPreexistentes;
import Database.DAODietas;
import Database.DAORutinas;
import Database.DBExeption;
import Grupo88.Inicio.Inicio;
import Grupo88.Login.Login;
import ObjetosDB.Complexiones;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Dietas;
import ObjetosDB.Rutinas;
import ObjetosDB.Usuario;

public class AltaUsuario extends MasterPage {	
	
	private FrmAltaUsuario frmAltaUsuario;
	private DAOComplexiones daoComplexiones = new DAOComplexiones(getSessionBD());
	private DAOCondicionesPreexistentes daoCondicionesPreexistentes = new DAOCondicionesPreexistentes(getSessionBD());
	private DAODietas daoDietas = new DAODietas(getSessionBD());
	private DAORutinas daoRutinas = new DAORutinas(getSessionBD());
	
	public AltaUsuario(){
		super();
		
		add(frmAltaUsuario = new FrmAltaUsuario("FrmAltaUsuario"));
	
	}
	
	private class FrmAltaUsuario extends Form {

		private Usuario usuario = new Usuario();
		private final ArrayList<estadoCondPreex> estados = new ArrayList<estadoCondPreex>();
		private FeedbackPanel feedback;
		private Label dbERROR;
		
		@SuppressWarnings("unchecked")
		public FrmAltaUsuario(String id) {
			super(id);		
			
			add(feedback = new FeedbackPanel("feedback"));
			feedback.setOutputMarkupId(true);
			add(dbERROR = new Label("DBERROR",Model.of("")));
			dbERROR.setOutputMarkupId(true);
			PasswordTextField password = new PasswordTextField("password", new PropertyModel<String>(usuario, "password"));
			PasswordTextField repPassword = new PasswordTextField("repPassword", Model.of("")); 
			
			try {
	
				add((new TextField("username", new PropertyModel<String>(usuario, "username")).add(new StringValidator(1,30))).setRequired(true));
				add(password);
				add(repPassword);
				add(new EqualPasswordInputValidator(password, repPassword));
				add(new EmailTextField("email", new PropertyModel<String>(usuario, "email")).add(EmailAddressValidator.getInstance()));
				add(new TextField("nombre", new PropertyModel<String>(usuario, "nombre")));
				add(new TextField("apellido", new PropertyModel<String>(usuario, "apellido")));{};
				add(new DropDownChoice<Character>("sexo", new PropertyModel<Character>(usuario, "sexo"), Arrays.asList('M', 'F')));
				add(new TextField<String>("fechaNac", new PropertyModel<String>(usuario, "fechaNacimiento")));
				add(new NumberTextField("altura", new PropertyModel<Integer>(usuario, "altura"), Integer.class));
				add(new DropDownChoice<Complexiones>("complexion", new PropertyModel<Complexiones>(usuario, "complexion"), daoComplexiones.findAll(), new ChoiceRenderer("complexion","idComplexion")));		
				
				//add(new DropDownChoice<PreferenciasAlimenticias>("preferencia", new PropertyModel<PreferenciasAlimenticias>(usuario, "preferencia"), Browser.listaPreferenciasAlimenticias(), new ChoiceRenderer("preferencia","idPreferencia")));
				
				Model<String> preferenciaActual = new Model<String>();
				add(new TextField<String>("preferencia",preferenciaActual));
				
				
				RepeatingView condiciones = new RepeatingView("grupoCheckBox");
				ArrayList<CondicionesPreexistentes> listaCondPreexistentes = new ArrayList<CondicionesPreexistentes>(daoCondicionesPreexistentes.findAll());
				
				for (CondicionesPreexistentes condPreex : listaCondPreexistentes) {
					
					AbstractItem item = new AbstractItem(condiciones.newChildId());
					
					estadoCondPreex actual = new estadoCondPreex(condPreex,new Model<Boolean>(false));
					estados.add(actual);
					
					item.add(new Label("textoCheckBox", actual.cond.getCondPreex()));
					item.add(new CheckBox("CheckBox", actual.modelCond));
					condiciones.add(item);
					
				}
				add(condiciones);	
			
				add(new DropDownChoice<Rutinas>("rutina", new PropertyModel<Rutinas>(usuario, "rutina"),daoRutinas.findAll(), new ChoiceRenderer("rutina","idRutina")));
				add(new DropDownChoice<Dietas>("dieta", new PropertyModel<Dietas>(usuario, "dieta"), daoDietas.findAll(), new ChoiceRenderer("dieta","idDieta")));
		    } catch (DBExeption e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				setResponsePage(new ErrorPage("error cargar items"));
				return;
			}
		   		    
		    add(new Link("cancelar"){
				
				@Override
				public void onClick() {
				
					setResponsePage(Login.class);
					
				}
			});	

		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			String msg = this.cargarDatosUsuario();
			if(msg != "" || !msg.isEmpty() || msg != null){
				setResponsePage(new ErrorPage(msg));
				return;
			}
			setResponsePage(Inicio.class);
			
		}
		private String cargarDatosUsuario(){
			
			for (estadoCondPreex estado : estados) {
				if(estado.modelCond.getObject())
				{
					usuario.setCondicion(estado.cond);
				}
			}

			ModelUsuario mUsuario = new ModelUsuario(usuario);
			return mUsuario.save(usuario);			
			
		}
	}
	
	private class estadoCondPreex{
		estadoCondPreex(CondicionesPreexistentes cond, IModel<Boolean> modelCond){
			this.cond = cond;
			this.modelCond = modelCond;
		}
		
		public CondicionesPreexistentes cond;
		public IModel<Boolean> modelCond;

		
	}
}