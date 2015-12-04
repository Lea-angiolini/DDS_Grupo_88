package Grupo88.Componentes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import ObjetosDB.AlimDeReceta;

public class DropList<T extends AlimDeReceta> extends Panel {


	private static final long serialVersionUID = 1L;
	private MarkupContainer contIng1;
	private MarkupContainer contIng2;
	private final ArrayList<Estado<T>> estados;
	private final Model<String> filtroIngText;
	
	public DropList(String id, ArrayList<T> lista) {
		super(id);
		
		estados = new ArrayList<Estado<T>>();
		
		for(T prod : lista){
			estados.add(new Estado<T>(prod));
		}
		
		filtroIngText = new Model<String>();
		final TextField<String> filtroIng = new TextField<String>("filtroIng", filtroIngText);
		
		filtroIng.add(new AjaxFormComponentUpdatingBehavior ("onkeyup") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				
				MarkupContainer nvoCont1;
				contIng1.replaceWith(nvoCont1 = generarListaIng(filtrarProductos(estados, filtroIngText),"contIng", false));
				target.add(nvoCont1);
				contIng1 = nvoCont1;
				
				
			}
		});
		add(filtroIng);
		
		
		add(contIng1 = generarListaIng(estados,"contIng", false));
		add(contIng2 = generarListaIng(estados,"contIng2", true));
		
	}
	
	
	private ArrayList<Estado<T>> filtrarProductos(ArrayList<Estado<T>> productos, Model<String> filtro){

		if(filtro.getObject() == null){
			return productos;
		}
		
		ArrayList<Estado<T>> filtrados = new ArrayList<Estado<T>>(); 
		
		for(Estado<T> prod : estados ){
			if(prod.getName().toLowerCase().startsWith(filtro.getObject().toLowerCase())){
				filtrados.add(prod);
			}
		}
		return filtrados;
	}
	
	
	private MarkupContainer generarListaIng(final ArrayList<Estado<T>> productos, String containerID, boolean estad){
		
		final MarkupContainer contenedor = new MarkupContainer(containerID){

			private static final long serialVersionUID = 1L;};
		
		RepeatingView ingRep = new RepeatingView("ingSel");
		for (final Estado<T> prod : productos){
			if(prod.isElegido() == estad){
			final AjaxLink<?> item = new AjaxLink<Object>(ingRep.newChildId()){

				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget target) {
					prod.alternar();
					
					MarkupContainer nvoCont1;
					contIng1.replaceWith(nvoCont1 = generarListaIng(filtrarProductos(productos, filtroIngText),"contIng", false));
					target.add(nvoCont1);
					contIng1 = nvoCont1;
					
					MarkupContainer nvoCont2;
					contIng2.replaceWith(nvoCont2 = generarListaIng(filtrarProductos(productos, new Model<String>("")),"contIng2", true));
					target.add(nvoCont2);
					contIng2 = nvoCont2;
				}
			};
			item.add(new Label("ingText", prod.getName()));
			ingRep.add(item);
			}
		}
		contenedor.add(ingRep);
		contenedor.setOutputMarkupId(true);
		return contenedor;
	}
	
	public Set<T> getElegidos(){
		Set<T> elegidos = new HashSet<T>();
		for(Estado<T> prod : estados ){
			if(prod.isElegido()){
				elegidos.add(prod.getObject());
			}
		}
		return elegidos;
	}
	
	
	@SuppressWarnings("hiding")
	private class Estado<T extends AlimDeReceta>{
		private T object;
		private boolean elegido;
		public Estado(T prod) {
			object = prod;
			elegido = false;
		}
		public T getObject() {
			return object;
		}
		public boolean isElegido() {
			return elegido;
		}
		public boolean alternar(){
			return (elegido = !elegido);
		}
		
		public String getName(){
			
			return object.getName();
			}
	}
}