package master;

import objetosWicket.SesionUsuario;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.*;
import org.omg.CORBA.PRIVATE_MEMBER;

import Grupo88.Estadisticas.Estadisticas.FrmEstadisticas;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Usuario;

public class ErrorPage extends MasterPage {
	private static final long serialVersionUID = 1L;
	private String msg;
	

	public static ErrorPage ErrorIngresoInvalidoDatos(){
		return new ErrorPage("Los campos no estan completados correctamente, revise por favor");
	}
	
	public static ErrorPage ErrorCargaDatos(){
		return new ErrorPage("Oops! Hubo algun problema interno, por favor vuelva a intentar mas tarde");
	}

	public static ErrorPage ErrorEnLaDB(){
		return new ErrorPage("Hubo problemas con la Base de Datos, intentelo nuevamente o comuniquese con el administrador");
	}
	
	public static ErrorPage ErrorRandom(){
		return new ErrorPage("Oops! La cagaste... Cerra todo antes de que se den cuenta");
	}
	
	
	
	
	public ErrorPage(String msg) {
		super();
		this.msg = msg;
		add(new FormError("frmERROR"));
		
    }
	
/*	public ErrorPage(){
		super();
		this.msg = "Oops! La cagaste... Cerra todo antes de que se den cuenta";
		add(new FormError ("FrmERROR"));
	} */

	private class FormError extends Form{
		public FormError(String id) {
			super(id);
			add(new Label("msgERROR", msg));
			add(new Link<Object>("irInicio"){
				@Override
				public void onClick() {
					setResponsePage(Inicio.class);
				}
			});
		}
	}
}
