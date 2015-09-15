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

public class ConsultasDificultad extends RegisteredPage{

	private FrmConsultasDificultad frmConsultasDificultad;
	public ConsultasDificultad(){
		super();
		//getMenuPanel().setVisible(false);
		
		add(frmConsultasDificultad = new FrmConsultasDificultad("frmConsultasDificultad"));

	}
	
	public class FrmConsultasDificultad extends Form{
		private char estadoSexo;
		public FrmConsultasDificultad(String id) {
			super(id);
			
			ArrayList<Consulta> consultasSemana = Browser.obtenerConsultasSegunDificultad(7);
			ArrayList<Consulta> consultasMes = Browser.obtenerConsultasSegunDificultad(30);
			
			add(generarLista("listaSemana", consultasSemana));
			add(generarLista("listaMes", consultasMes));
		}
	}
	
	private RepeatingView generarLista(String id, ArrayList<Consulta> listaConsultas){
		
		RepeatingView lista = new RepeatingView(id);
		
		for (Consulta dificultad : listaConsultas){
			AbstractItem item = new AbstractItem(lista.newChildId());
			
			item.add(new Label("dificultad",dificultad.getNombre()));
			item.add(new Label("cantidad",dificultad.getCantidad()));
			lista.add(item);
		}
		return lista;
	}
}
