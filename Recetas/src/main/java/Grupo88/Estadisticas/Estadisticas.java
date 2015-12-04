package Grupo88.Estadisticas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import master.RegisteredPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import Database.DAOEstadistica;
import Database.DAOEstadisticaPorSexo;
import Database.StrategyEstadisticas;
import ObjetosDB.Consulta;

public class Estadisticas extends RegisteredPage{

	private static final long serialVersionUID = 1L;
	private DAOEstadistica daoEstadistica;
	@SuppressWarnings("unused")
	private FrmEstadisticas frmEstadisticas;
	private ArrayList<DAOEstadistica> listaDAO;
	private List<Integer> fechas;
	
	
	public Estadisticas(DAOEstadistica dao){
		super();
		listaDAO = new ArrayList<DAOEstadistica>();
		listaDAO.add(dao);
		add(frmEstadisticas = new FrmEstadisticas("frmEstadisticas"));
		fechas = Arrays.asList(10,30);
	}
	
	public class FrmEstadisticas extends Form<Object>{
		
		private static final long serialVersionUID = 1L;

		public FrmEstadisticas(String id) {
			super(id);
			
			final DataView<Object> dataView = new DataView<Object>("iteradorTabla", new ListDataProvider(
	                listaDAO)) {

				private static final long serialVersionUID = -4285846135742773862L;

				protected void populateItem(final Item item){
	            	final DAOEstadistica dao = (DAOEstadistica) item.getModelObject();
	                item.add(new Label("estadistica", dao.descripcionEst()));
	         
	                RepeatingView col = new RepeatingView("iteradorCol");
	                	
	                	for(Integer f : fechas){
	                		AbstractItem celda = new AbstractItem(col.newChildId());
	                		
	                		try {
	                			celda.add(new Label("tiempo", f+" dias"));
								celda.add(new PanelTiposRecetasConsultadas("panel", dao.obtenerEstadistica(f)));
							} catch (Exception e) {
								celda.add(new EmptyPanel("panel"));
							}
	                		col.add(celda);
	                	}
	                item.add(col);
	            }
	        };
	        
	        add(dataView);	
	}
}
}