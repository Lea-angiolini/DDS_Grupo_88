package Grupo88.Estadisticas;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import ObjetosDB.Estadistico;

public class PanelTiposRecetasConsultadas extends Panel {

	private static final long serialVersionUID = -2960629591486735319L;

	public PanelTiposRecetasConsultadas(String id, ArrayList<Estadistico> estadisticos) {
		super(id);
		
		RepeatingView vista = new RepeatingView("iterador");

		for(Estadistico est : estadisticos){
			AbstractItem item = new AbstractItem(vista.newChildId());
			item.add(new Label("descripcion", est.getDescripcion()));
			item.add(new Label("valor", est.getValor()));
			
			vista.add(item);
		}
		
		add(vista);
	}
}
