package Grupo88.Reportes;

import org.apache.wicket.markup.html.form.Form;

import Database.StrategyReportes;
import Grupo88.Estadisticas.PanelDeEstadisticas;
import master.RegisteredPage;

public class Reportes extends RegisteredPage{
	
	public Reportes(StrategyReportes fabReporte) {
		// TODO Auto-generated constructor stub
		add(new FormReporte("frmReportes",fabReporte));
	}
	
	
	private class FormReporte extends Form{

		public FormReporte(String id,StrategyReportes fabReporte) {
			super(id);
			// TODO Auto-generated constructor stub
			add(new PanelDeEstadisticas("panelReporte", fabReporte.obtenerReporte(getUsuarioActual(), "2015-03-21", "2015-10-21")));
		}
		
	}
}
