package Grupo88.AltaUsuario;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import master.ErrorPage;
import master.MasterPage;

import org.apache.wicket.MarkupContainer;
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
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

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
		private final MarkupContainer error;
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public FrmAltaUsuario(String id) {
			super(id);		
			
			add(error = new MarkupContainer("error"){
				private static final long serialVersionUID = 6315760448959379801L;});
			
			error.setVisible(false);
			error.add(new FeedbackPanel("feedback"));
			error.setOutputMarkupId(true);
			
			PasswordTextField password = new PasswordTextField("password", new PropertyModel<String>(usuario, "password"));
			PasswordTextField repPassword = new PasswordTextField("repPassword", Model.of("")); 
			
			try {
	
				add(new TextField<String>("username", new PropertyModel<String>(usuario, "username")));
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
				private static final long serialVersionUID = 8895339676060670334L;

				@Override
				public void onClick() {
				
					setResponsePage(Inicio.class);
					
				}
			});	

		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			daoUsuarios = new DAOUsuarios(getSessionBD());
			try {
				try{
				daoUsuarios.save(usuario);
				setResponsePage(new ErrorPage("Ha sido registrado satisfactoriamente \n Ya puede iniciar sesion"));
				}
				catch(javax.validation.ConstraintViolationException cve){
					info(cve.getConstraintViolations().iterator().next().getMessage());
				}
				catch (org.hibernate.exception.ConstraintViolationException cve) {
					info(cve.getMessage());
				}
				finally{
				error.setVisible(true);
				}
			} catch (Exception e) {
				setResponsePage(new ErrorPage("Parece que hubo un error. Intentelo mas tarde "+e.getMessage()));
				
			}
		}
	}
}