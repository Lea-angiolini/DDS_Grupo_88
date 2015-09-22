package Grupo88.Estadisticas;

import java.util.ArrayList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import ObjetosDB.Consulta;

public class PanelDeEstadisticas extends Panel{
	
	public PanelDeEstadisticas(String id, ArrayList<Consulta> listaConsultas) {
		// TODO Auto-generated constructor stub
		super(id);
		
		RepeatingView lista = new RepeatingView("lista");
		
		for (Consulta dificultad : listaConsultas){
			AbstractItem item = new AbstractItem(lista.newChildId());
			
			item.add(new Label("descripcion",dificultad.getNombre()));
			item.add(new Label("cantidad",dificultad.getCantidad()));
			lista.add(item);
		}
		add(lista);
	}

}
