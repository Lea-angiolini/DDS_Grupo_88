package Grupo88.Estadisticas;

import java.util.ArrayList;

import master.RegisteredPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.repeater.RepeatingView;

import ObjetosDB.Estadistico;

public class Estadisticas extends RegisteredPage{

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private FrmEstadisticas frmEstadisticas;
	private NegocioEstadistica negocio;
	
	public Estadisticas(NegocioEstadistica negocio){
		super();
		this.negocio = negocio;
		add(frmEstadisticas = new FrmEstadisticas("frmEstadisticas"));
	}
	
	public class FrmEstadisticas extends Form<Object>{
		
		private static final long serialVersionUID = 1L;

		public FrmEstadisticas(String id) {
			super(id);
			
			add(new Label("estadistica", negocio.descripcionEst()));
	                RepeatingView col = new RepeatingView("iteradorCol");
	                	
	                	for(Integer f : negocio.getFechas()){
	                		AbstractItem celda = new AbstractItem(col.newChildId());
	                		
	                		ArrayList<Estadistico> estadisticos = negocio.obtenerEstadistica(f);
	                		
	                		if(estadisticos != null){
	                			celda.add(new Label("tiempo", f+" dias"));
								celda.add(new PanelTiposRecetasConsultadas("panel", estadisticos));
	                		}
							else {
								celda.add(new EmptyPanel("panel"));
							}
	                		
	                		col.add(celda);
	                	}
	                add(col);
	            }

	}
}
