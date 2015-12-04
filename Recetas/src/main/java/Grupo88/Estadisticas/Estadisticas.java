package Grupo88.Estadisticas;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;

import Database.DAOEstadisticaPorSexo;
import Database.StrategyEstadisticas;
import ObjetosDB.Consulta;

public class Estadisticas extends RegisteredPage{

	private static final long serialVersionUID = 1L;
	private DAOEstadisticaPorSexo daoEstadisticaPorSexo;
	@SuppressWarnings("unused")
	private FrmEstadisticas frmEstadisticas;

	public Estadisticas(){
		super();
		
		daoEstadisticaPorSexo = new DAOEstadisticaPorSexo(getSessionBD());
		add(frmEstadisticas = new FrmEstadisticas("frmEstadisticas"));
		
	}
	
	public class FrmEstadisticas extends Form<Object>{
		
		private static final long serialVersionUID = 1L;

		public FrmEstadisticas(String id) {
			super(id);
			
			try {
				add(new PanelTiposRecetasConsultadas("estadisticasMes", daoEstadisticaPorSexo.obtenerEstadistica(30)));
				add(new PanelTiposRecetasConsultadas("estadisticasSemana", daoEstadisticaPorSexo.obtenerEstadistica(7)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
	}
}
}