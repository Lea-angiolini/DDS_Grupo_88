package Grupo88.AltaUsuario;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;

import master.ErrorPage;
import master.MasterPage;

import org.apache.wicket.datetime.DateConverter;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DateField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
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
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.tool.hbm2ddl.ImportScriptException;
import org.joda.time.format.DateTimeFormatter;

import Database.DAOComplexiones;
import Database.DAOCondicionesPreexistentes;
import Database.DAODietas;
import Database.DAOPreferenciasAlimenticias;
import Database.DAORutinas;
import Database.DAOUsuarios;
import Database.DBExeption;
import Grupo88.Inicio.Inicio;
import Grupo88.Login.Login;
import ObjetosDB.Complexiones;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Dietas;
import ObjetosDB.PreferenciasAlimenticias;
import ObjetosDB.Rutinas;
import ObjetosDB.Usuario;

public class AltaUsuario extends MasterPage {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private FrmAltaUsuario frmAltaUsuario;
	private DAOComplexiones daoComplexiones = new DAOComplexiones(getSessionBD());
	private DAOCondicionesPreexistentes daoCondicionesPreexistentes = new DAOCondicionesPreexistentes(getSessionBD());
	private DAODietas daoDietas = new DAODietas(getSessionBD());
	private DAORutinas daoRutinas = new DAORutinas(getSessionBD());
	private DAOPreferenciasAlimenticias daoPreferenciasAlimenticias = new DAOPreferenciasAlimenticias(getSessionBD());
	private DAOUsuarios daoUsuarios;
	
	public AltaUsuario(){
		super();
		
		add(frmAltaUsuario = new FrmAltaUsuario("FrmAltaUsuario"));
	
	}
	
	private class FrmAltaUsuario extends Form<Object> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Usuario usuario = new Usuario();
		private FeedbackPanel feedback;
		
		@SuppressWarnings("rawtypes")
		public FrmAltaUsuario(String id) {
			super(id);		
			
			add(feedback = new FeedbackPanel("feedback"));
			feedback.setOutputMarkupId(true);
			PasswordTextField password = new PasswordTextField("password", new PropertyModel<String>(usuario, "password"));
			PasswordTextField repPassword = new PasswordTextField("repPassword", Model.of("")); 
			
			try {
	
				add((new TextField<String>("username", new PropertyModel<String>(usuario, "username")).add(new StringValidator(1,30))).setRequired(true));
				add(password);
				add(repPassword);
				add(new EqualPasswordInputValidator(password, repPassword));
				add(new EmailTextField("email", new PropertyModel<String>(usuario, "email")).add(EmailAddressValidator.getInstance()));
				add(new TextField<String>("nombre", new PropertyModel<String>(usuario, "nombre")));
				add(new TextField<String>("apellido", new PropertyModel<String>(usuario, "apellido")));{};
				add(new DropDownChoice<Character>("sexo", new PropertyModel<Character>(usuario, "sexo"), Arrays.asList('M', 'F')));
				add(new TextField<String>("fechaNac", new PropertyModel<String>(usuario, "fechaNacimiento")));
				add(new NumberTextField<Integer>("altura", new PropertyModel<Integer>(usuario, "altura"), Integer.class));
				add(new DropDownChoice<Complexiones>("complexion", new PropertyModel<Complexiones>(usuario, "complexion"), daoComplexiones.findAll(), new ChoiceRenderer<Complexiones>("complexion","idComplexion")));		
				add(new CheckBoxMultipleChoice<PreferenciasAlimenticias>("preferencia",new PropertyModel(usuario,"preferencias"), new ArrayList(daoPreferenciasAlimenticias.findAll()), new ChoiceRenderer("preferencia","idPreferencia")));
				add(new CheckBoxMultipleChoice<CondicionesPreexistentes>("condPreex",new PropertyModel(usuario,"condiciones"), new ArrayList(daoCondicionesPreexistentes.findAll()), new ChoiceRenderer("condPreex","idCondPreex")));
				add(new DropDownChoice<Rutinas>("rutina", new PropertyModel<Rutinas>(usuario, "rutina"),daoRutinas.findAll(), new ChoiceRenderer<Object>("rutina","idRutina")));
				add(new DropDownChoice<Dietas>("dieta", new PropertyModel<Dietas>(usuario, "dieta"), daoDietas.findAll(), new ChoiceRenderer<Object>("dieta","idDieta")));
		    } catch (DBExeption e) {
				e.printStackTrace();
				setResponsePage(new ErrorPage("error cargar items " + e.getMessage()));
				return;
			}
		   		    
		    add(new Link<Object>("cancelar"){
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
				
					setResponsePage(Login.class);
					
				}
			});	

		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			daoUsuarios = new DAOUsuarios(getSessionBD());
			try {
				daoUsuarios.saveOrUpdate(usuario);
			} catch (DBExeption e) {
				setResponsePage(new ErrorPage(e.getMessage()));
			}
			
			setResponsePage(new ErrorPage("Ha sido registrado satisfactoriamente \n Ya puede iniciar sesion"));
			
		}
	}
}