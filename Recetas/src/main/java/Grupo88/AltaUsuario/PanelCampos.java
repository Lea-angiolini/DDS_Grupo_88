package Grupo88.AltaUsuario;

import java.util.ArrayList;

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
import Database.DAOSexo;
import Database.DBExeption;
import ObjetosDB.Complexiones;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Dietas;
import ObjetosDB.PreferenciasAlimenticias;
import ObjetosDB.Rutinas;
import ObjetosDB.Sexo;
import ObjetosDB.Usuario;

public class PanelCampos extends Panel {

	private static final long serialVersionUID = 8239928352700688677L;
	
	private DAOComplexiones daoComplexiones;
	private DAOCondicionesPreexistentes daoCondicionesPreexistentes;
	private DAODietas daoDietas;
	private DAORutinas daoRutinas;
	private DAOPreferenciasAlimenticias daoPreferenciasAlimenticias;
	private DAOSexo daoSexo;
	private Usuario usuario;
	
	public void crearDaos(Session session){
		
		daoComplexiones =  new DAOComplexiones(session);
		daoCondicionesPreexistentes = new DAOCondicionesPreexistentes(session);
		daoDietas = new DAODietas(session);
		daoRutinas = new DAORutinas(session);
		daoPreferenciasAlimenticias = new DAOPreferenciasAlimenticias(session);
		daoSexo = new DAOSexo(session);
	}
	
	public void adherirCamposPersonales(){
		
		DropDownChoice<Sexo> sexo;
		DropDownChoice<Complexiones> complexion;
		CheckBoxMultipleChoice<PreferenciasAlimenticias> preferencias;
		CheckBoxMultipleChoice<CondicionesPreexistentes> condiciones;
		DropDownChoice<Rutinas> rutina;
		DropDownChoice<Dietas> dieta;
		
		ArrayList<Sexo> todosSexos;
		ArrayList<Complexiones> todasComplexiones;
		ArrayList<PreferenciasAlimenticias> todasPreferenciasAlim;
		ArrayList<CondicionesPreexistentes> todasCondiciones;
		ArrayList<Rutinas> todasRutinas;
		ArrayList<Dietas> todasDietas;
		
		
		try {
			
			todosSexos =  new ArrayList<Sexo>(daoSexo.findAll());
			todasComplexiones = new ArrayList<Complexiones>(daoComplexiones.findAll());
			todasPreferenciasAlim = new ArrayList<PreferenciasAlimenticias>(daoPreferenciasAlimenticias.findAll());
			todasCondiciones = new ArrayList<CondicionesPreexistentes>(daoCondicionesPreexistentes.findAll());
			todasRutinas = new ArrayList<Rutinas>(daoRutinas.findAll());
			todasDietas = new ArrayList<Dietas>(daoDietas.findAll());
			
	    } catch (DBExeption e) {
			e.printStackTrace();
			setResponsePage(ErrorPage.ErrorCargaDatos()); 
			return;
		}
		
		add(new EmailTextField("email", new PropertyModel<String>(usuario, "email")).add(EmailAddressValidator.getInstance()));
		add(new TextField<String>("nombre", new PropertyModel<String>(usuario, "nombre")));
		add(new TextField<String>("apellido", new PropertyModel<String>(usuario, "apellido")));{};
		add(sexo = new DropDownChoice<Sexo>("sexo", new PropertyModel<Sexo>(usuario, "sexo"), todosSexos, new ChoiceRenderer<Sexo>("descripcion", "idSexo")));
		add(new TextField<String>("fechaNac", new PropertyModel<String>(usuario, "fechaNacimiento")));
		add(new NumberTextField<Integer>("altura", new PropertyModel<Integer>(usuario, "altura"), Integer.class));
		add(complexion = new DropDownChoice<Complexiones>("complexion", new PropertyModel<Complexiones>(usuario, "complexion"), todasComplexiones, new ChoiceRenderer<Complexiones>("complexion","idComplexion")));
		add(preferencias = new CheckBoxMultipleChoice<PreferenciasAlimenticias>("preferencia",new PropertyModel(usuario,"preferencias"), todasPreferenciasAlim, new ChoiceRenderer("preferencia","idPreferencia")));
		add(condiciones = new CheckBoxMultipleChoice<CondicionesPreexistentes>("condPreex",new PropertyModel(usuario,"condiciones"), todasCondiciones, new ChoiceRenderer("condPreex","idCondPreex")));
		add(rutina = new DropDownChoice<Rutinas>("rutina", new PropertyModel<Rutinas>(usuario, "rutina"),todasRutinas, new ChoiceRenderer<Object>("rutina","idRutina")));
		add(dieta = new DropDownChoice<Dietas>("dieta", new PropertyModel<Dietas>(usuario, "dieta"), todasDietas, new ChoiceRenderer<Object>("dieta","idDieta")));
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
