package Grupo88.Componentes;

import java.util.ArrayList;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import ObjetosDB.AlimDeReceta;

public class DragAndDropAlim extends Panel{

	private static final long serialVersionUID = -1406633677855321873L;
	private ArrayList<AlimDeReceta> alimentosNo;
	private ArrayList<AlimDeReceta> alimentosSi;
	private AlimDeReceta onDrag;
	private MarkupContainer listaNO;
	private MarkupContainer listaSI;
	
	public DragAndDropAlim(String id, ArrayList<AlimDeReceta> alims) {
		super(id);
	
		alimentosNo = new ArrayList<AlimDeReceta>();
		alimentosSi = new ArrayList<AlimDeReceta>();
		alimentosNo.addAll(alims);

		listaNO = generarListaNO(alimentosNo);
		listaNO.setOutputMarkupId(true);
		
		listaSI = generarListaSI(alimentosSi);
		listaSI.setOutputMarkupId(true);
		

		add(listaNO);
		add(listaSI);
	}
		
	private MarkupContainer generarListaNO(ArrayList<AlimDeReceta> lista){
		
		RepeatingView repLista = new RepeatingView("listado");

		for(final AlimDeReceta alim : lista){
			final MarkupContainer item = new MarkupContainer(repLista.newChildId()){

			private static final long serialVersionUID = 1L;};
			final Label text;
			item.add(text = new Label("name",alim.getName()));
			text.add(new AjaxEventBehavior("ondragstart") {
				
				private static final long serialVersionUID = 1L;

				@Override
				protected void onEvent(AjaxRequestTarget target) {
					onDrag = alim;
				}
			});
			repLista.add(item);
		}
		
		MarkupContainer dev = new MarkupContainer("listaNO"){

			private static final long serialVersionUID = 1L;};
		dev.add(repLista);
		return dev;
}
	
	private MarkupContainer generarListaSI(ArrayList<AlimDeReceta> lista){
		
		RepeatingView repLista = new RepeatingView("listadoAcept");

		for(final AlimDeReceta alim : lista){
			final MarkupContainer item = new MarkupContainer(repLista.newChildId()){

			private static final long serialVersionUID = 1L;};
			final Label text;
			item.add(text = new Label("name",alim.getName()));
			text.add();
			repLista.add(item);
		}
		
		MarkupContainer dev = new MarkupContainer("listaSI"){

			private static final long serialVersionUID = 1L;};
		dev.add(new AjaxEventBehavior("ondrag"){

			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				alimentosNo.remove(onDrag);
				alimentosSi.add(onDrag);
				listaNO = generarListaNO(alimentosNo);
				target.add(listaNO);
			}
		});
		dev.add(repLista);
		return dev;
}
}
