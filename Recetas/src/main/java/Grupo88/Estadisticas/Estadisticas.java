package Grupo88.Estadisticas;

import java.util.ArrayList;

import master.RegisteredPage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;

import Database.Browser;

public class Estadisticas extends RegisteredPage{
	//private static final long serialVersionUID = 1L;
	//private Usuario user = ((SesionUsuario)getSession()).getUsuario().getObject();
	private FrmEstadisticas frmEstadisticas;
	private AbstractItem semanal;
	private AbstractItem mensual;
	public Estadisticas(){
		super();
		//getMenuPanel().setVisible(false);
		
		add(frmEstadisticas = new FrmEstadisticas("frmEstadisticas"));

	}
	
	public class FrmEstadisticas extends Form{
		private char estadoSexo;
		public FrmEstadisticas(String id) {
			super(id);
			
			final ObjetosDB.Estadisticas est = Browser.obtenerEstadisticas();			
			
			
			add(new AjaxLink("cambiarSexo") {
				public void onClick(AjaxRequestTarget target) {
					// TODO Auto-generated method stub				
					
					ArrayList<String> semana;
					ArrayList<String> mes;
					
					if(estadoSexo == 'M'){
						semana = est.getTopRecetasMujerSemana();
						mes = est.getTopRecetasMujerMes();
						estadoSexo = 'F';
						target.appendJavaScript("hombre();");
					}
					else
					{
						semana = est.getTopRecetasHombreSemana();
						mes = est.getTopRecetasHombreMes();
						estadoSexo = 'M';
						target.appendJavaScript("mujer();");
					}
					
					
					AbstractItem nvosem =  new AbstractItem("Semsexo");
					nvosem.add(generarLista("ultSem", semana));
					
					nvosem.setOutputMarkupId(true);
					semanal.replaceWith(nvosem);
					target.add(nvosem);
					semanal = nvosem;
					
					AbstractItem nvomes =  new AbstractItem("Messexo");
					nvomes.add(generarLista("ultMes", mes ));
					
					nvomes.setOutputMarkupId(true);
					mensual.replaceWith(nvomes);
					target.add(nvomes);
					mensual = nvomes;
				}
			});
			
			estadoSexo = 'M';
			semanal = new AbstractItem("Semsexo");
			semanal.setOutputMarkupId(true);
			semanal.add(generarLista("ultSem", est.getTopRecetasHombreSemana()));
			add(semanal);
			
			mensual = new AbstractItem("Messexo");
			mensual.setOutputMarkupId(true);
			mensual.add(generarLista("ultMes", est.getTopRecetasHombreMes()));
			add(mensual);
	}
	}
	
	private RepeatingView generarLista(String id, ArrayList<String> listaRecetas){
		
		RepeatingView lista = new RepeatingView(id);
		//lista.setOutputMarkupId(true);
		for (String receta : listaRecetas){
			AbstractItem item = new AbstractItem(lista.newChildId());
			
			item.add(new Label("nombreReceta",receta));
			lista.add(item);
		}
		return lista;
	}
}
