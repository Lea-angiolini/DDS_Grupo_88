package Grupo88.Reportes;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;

public class Reportes extends RegisteredPage{
	

	private static final long serialVersionUID = 1L;


	public Reportes() {
		// TODO Auto-generated constructor stub
		//add(new FormReporte("frmReportes",fabReporte));
	}
	
	
	private class FormReporte extends Form<Object>{


		private static final long serialVersionUID = 1L;

		public FormReporte(String id) {
			super(id);
			// TODO Auto-generated constructor stub
			//add(new PanelDeEstadisticas("panelReporte", fabReporte.obtenerReporte(getUsuarioActual(), "2015-03-21", "2015-10-21")));
		}
		
	}
}
