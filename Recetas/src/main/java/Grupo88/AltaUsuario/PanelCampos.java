package Grupo88.AltaUsuario;

import java.io.Serializable;
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

import ObjetosDB.Complexiones;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Dietas;
import ObjetosDB.PreferenciasAlimenticias;
import ObjetosDB.Rutinas;
import ObjetosDB.Sexo;
import ObjetosDB.Usuario;

public class PanelCampos extends Panel implements Serializable{

	private static final long serialVersionUID = 8239928352700688677L;
	
	private Usuario usuario;
	private NegocioAltaUsuario negocio;
	
	
	public void adherirCamposPersonales(){
		
		DropDownChoice<Sexo> sexo;
		DropDownChoice<Complexiones> complexion;
		CheckBoxMultipleChoice<PreferenciasAlimenticias> preferencias;
		CheckBoxMultipleChoice<CondicionesPreexistentes> condiciones;
		DropDownChoice<Rutinas> rutina;
		DropDownChoice<Dietas> dieta;
		
		if(!negocio.cargarListas())
			setResponsePage(ErrorPage.ErrorCargaDatos());
		
		
		add(new EmailTextField("email", new PropertyModel<String>(usuario, "email")).add(EmailAddressValidator.getInstance()));
		add(new TextField<String>("nombre", new PropertyModel<String>(usuario, "nombre")));
		add(new TextField<String>("apellido", new PropertyModel<String>(usuario, "apellido")));{};
		add(sexo = new DropDownChoice<Sexo>("sexo", new PropertyModel<Sexo>(usuario, "sexo"), negocio.getTodosSexos(), new ChoiceRenderer<Sexo>("descripcion", "idSexo")));
		add(new TextField<String>("fechaNac", new PropertyModel<String>(usuario, "fechaNacimiento")));
		add(new NumberTextField<Integer>("altura", new PropertyModel<Integer>(usuario, "altura"), Integer.class));
		add(complexion = new DropDownChoice<Complexiones>("complexion", new PropertyModel<Complexiones>(usuario, "complexion"), negocio.getTodasComplexiones(), new ChoiceRenderer<Complexiones>("complexion","idComplexion")));
		add(preferencias = new CheckBoxMultipleChoice<PreferenciasAlimenticias>("preferencia",new PropertyModel(usuario,"preferencias"), negocio.getTodasPreferenciasAlim(), new ChoiceRenderer("preferencia","idPreferencia")));
		add(condiciones = new CheckBoxMultipleChoice<CondicionesPreexistentes>("condPreex",new PropertyModel(usuario,"condiciones"), negocio.getTodasCondiciones(), new ChoiceRenderer("condPreex","idCondPreex")));
		add(rutina = new DropDownChoice<Rutinas>("rutina", new PropertyModel<Rutinas>(usuario, "rutina"),negocio.getTodasRutinas(), new ChoiceRenderer<Object>("rutina","idRutina")));
		add(dieta = new DropDownChoice<Dietas>("dieta", new PropertyModel<Dietas>(usuario, "dieta"), negocio.getTodasDietas(), new ChoiceRenderer<Object>("dieta","idDieta")));
	}
	
	public PanelCampos(String id, NegocioAltaUsuario negocio) {
		super(id);
		this.usuario = new Usuario();
		this.negocio = negocio;
		
		add(new fragmentoDatosCuenta("DatosCuenta", "camposCuenta", this));
		adherirCamposPersonales();
		
	}
	
	public PanelCampos(String id, NegocioAltaUsuario negocio, Usuario usuario) {
		super(id);
		this.usuario = usuario;
		
		this.negocio = negocio;
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
