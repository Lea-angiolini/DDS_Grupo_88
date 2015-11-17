package Grupo88.AltaUsuario;

import java.util.ArrayList;
import java.util.Arrays;

import master.ErrorPage;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.hibernate.Session;

import Database.DAOComplexiones;
import Database.DAOCondicionesPreexistentes;
import Database.DAODietas;
import Database.DAOPreferenciasAlimenticias;
import Database.DAORutinas;
import Database.DBExeption;
import ObjetosDB.Complexiones;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Dietas;
import ObjetosDB.PreferenciasAlimenticias;
import ObjetosDB.Rutinas;
import ObjetosDB.Usuario;

public class PanelCampos extends Panel {

	private static final long serialVersionUID = 8239928352700688677L;
	
	private DAOComplexiones daoComplexiones;
	private DAOCondicionesPreexistentes daoCondicionesPreexistentes;
	private DAODietas daoDietas;
	private DAORutinas daoRutinas;
	private DAOPreferenciasAlimenticias daoPreferenciasAlimenticias;
	private Usuario usuario;
	
	public void crearDaos(Session session){
		
		daoComplexiones =  new DAOComplexiones(session);
		daoCondicionesPreexistentes = new DAOCondicionesPreexistentes(session);
		daoDietas = new DAODietas(session);
		daoRutinas = new DAORutinas(session);
		daoPreferenciasAlimenticias = new DAOPreferenciasAlimenticias(session); 	
	}
	
	public void adherirCamposPersonales(){
		try {
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
	}
	
	public PanelCampos(String id, Session session) {
		super(id);
		this.usuario = new Usuario();
		
		crearDaos(session);
		add(new fragmentoDatosCuenta("DatosCuenta", "camposCuenta", this));
		adherirCamposPersonales();
		
	}
	
	public PanelCampos(String id, Session session, Usuario usuario) {
		super(id);
		this.usuario = usuario;
		
		crearDaos(session);
		add(new EmptyPanel("DatosCuenta"));
		adherirCamposPersonales();
		
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	private class fragmentoDatosCuenta extends Fragment{
		public fragmentoDatosCuenta(String id, String markupId,MarkupContainer markupProvider) {
			super(id,markupId,markupProvider);
			
			PasswordTextField password = new PasswordTextField("password", new PropertyModel<String>(usuario, "password"));
			PasswordTextField repPassword = new PasswordTextField("repPassword", Model.of(""));
			
			add(new TextField<String>("username", new PropertyModel<String>(usuario, "username")));
			add(password);
			add(repPassword);
			add(new EqualPasswordInputValidator(password, repPassword));
		}
	}
}
