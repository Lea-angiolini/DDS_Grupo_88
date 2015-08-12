package Grupo88;

import java.util.ArrayList;

import master.MasterPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;

import Database.Browser;

public class Estadisticas extends MasterPage{
	//private static final long serialVersionUID = 1L;
	//private Usuario user = ((SesionUsuario)getSession()).getUsuario().getObject();
	
	public Estadisticas(){
		super();
		getMenuPanel().setVisible(false);
		
		add(new FrmEstadisticas("frmEstadisticas"));
	}
	
	public class FrmEstadisticas extends Form{
		public FrmEstadisticas(String id) {
			super(id);
			ObjetosDB.Estadisticas est = Browser.obtenerEstadisticas();
			ArrayList<String> top;
			String html = "";
			
			top = est.getTopRecetasHombre();
			for(String receta : top){
				html += createDynamicHtml(receta);
			}
			add(new Label("TOPHtipos", html).setEscapeModelStrings(false));
			
			html = "";
			top = est.getTopRecetasMujer();
			for(String receta : top){
				html += createDynamicHtml(receta);
			}
			add(new Label("TOPMtipos", html).setEscapeModelStrings(false));
			
			html = "";
			top = est.getTopDificultad();
			for(String receta : top){
				html += createDynamicHtml(receta);
			}
			add(new Label("TOPdificultad", html).setEscapeModelStrings(false));
			
			html = "";
			top = est.getTopRecetas();
			for(String receta : top){
				html += createDynamicHtml(receta);
			}
			add(new Label("TOPrecetas", html).setEscapeModelStrings(false));
			
		}
	}
		
	public String createDynamicHtml(String contenido) {
		StringBuilder divSB = new StringBuilder(512);

		divSB.append("<div>");
		divSB.append(contenido);
		divSB.append("</div>");

		return divSB.toString();
	}
}
