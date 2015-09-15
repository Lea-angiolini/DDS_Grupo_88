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
import ObjetosDB.Consulta;

public class TopConsultasRecetas extends RegisteredPage{

	private FrmTopConsultaRecetas frmTopConsultaRecetas;
	public TopConsultasRecetas(){
		super();
		//getMenuPanel().setVisible(false);
		
		add(frmTopConsultaRecetas = new FrmTopConsultaRecetas("frmTopConsultaRecetas"));

	}
	
	public class FrmTopConsultaRecetas extends Form{
		private char estadoSexo;
		public FrmTopConsultaRecetas(String id) {
			super(id);
			
			ArrayList<Consulta> consultasSemana = Browser.obtenerTopRecetasMasConsultadas(7);
			ArrayList<Consulta> consultasMes = Browser.obtenerTopRecetasMasConsultadas(30);
			
			add(generarLista("listaSemana", consultasSemana));
			add(generarLista("listaMes", consultasMes));
		}
	}
	
	private RepeatingView generarLista(String id, ArrayList<Consulta> listaConsultas){
		
		RepeatingView lista = new RepeatingView(id);
		
		for (Consulta receta : listaConsultas){
			AbstractItem item = new AbstractItem(lista.newChildId());
			
			item.add(new Label("receta",receta.getNombre()));
			item.add(new Label("cantidad",receta.getCantidad()));
			lista.add(item);
		}
		return lista;
	}
}
