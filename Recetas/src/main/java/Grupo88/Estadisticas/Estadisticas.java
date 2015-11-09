package Grupo88.Estadisticas;

import java.util.ArrayList;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;

import Database.StrategyEstadisticas;
import ObjetosDB.Consulta;

public class Estadisticas extends RegisteredPage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = 1L;
	//private Usuario user = ((SesionUsuario)getSession()).getUsuario().getObject();
	@SuppressWarnings("unused")
	private FrmEstadisticas frmEstadisticas;
	private ArrayList<ArrayList<Consulta>> semanal;
	private ArrayList<ArrayList<Consulta>> mensual;
	
	public Estadisticas(StrategyEstadisticas factoryEstadistica){
		super();
		//getMenuPanel().setVisible(false);
		
		semanal = factoryEstadistica.ObtenerConsulta(7);
		mensual = factoryEstadistica.ObtenerConsulta(30);
		add(frmEstadisticas = new FrmEstadisticas("frmEstadisticas"));
		
	}
	
	public class FrmEstadisticas extends Form<Object>{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FrmEstadisticas(String id) {
			super(id);
			
			RepeatingView lista = new RepeatingView("grupoestadistica");

			for (int i = 0; i < semanal.size(); i++){
				AbstractItem item = new AbstractItem(lista.newChildId());
				
				item.add(new PanelDeEstadisticas("semana",semanal.get(i)));
				item.add(new PanelDeEstadisticas("mes",mensual.get(i)));
				lista.add(item);
			}
		add(lista);
	}
}
}