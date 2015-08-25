package Grupo88;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import objetosWicket.ModelUsuario;
import objetosWicket.SesionUsuario;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import Database.Browser;
import ObjetosDB.Condimentos;
import ObjetosDB.Ingredientes;

public class DropList<T> extends Panel {

	MarkupContainer contIng1;
	MarkupContainer contIng2;
	
	public DropList(String id, ArrayList<T> lista) {
		super(id);
		
		final ArrayList<Estado<T>> estados = new ArrayList<Estado<T>>();
		for(T prod : lista){
			estados.add(new Estado<T>(prod));
		}
		
		final Model<String> filtroIngText = new Model<String>();
		final TextField<String> filtroIng = new TextField<String>("filtroIng", filtroIngText);
		filtroIng.add(new AjaxFormComponentUpdatingBehavior ("onkeyup") {
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				target.add(filtroIng);
				/*ArrayList<Estado<Ingredientes>> nvoEstadosIng;
				for(Estado<Ingredientes> ing : estadosIng){
					if(ing.getObject().getIngrediente());
				}
				
				MarkupContainer nvoCont1;
				contIng1.replaceWith(nvoCont1 = generarListaIng(estadosIng,"contIng", false));
				target.add(nvoCont1);
				contIng1 = nvoCont1;
				
				MarkupContainer nvoCont2;
				contIng2.replaceWith(nvoCont2 = generarListaIng(estadosIng,"contIng2", true));
				target.add(nvoCont2);
				contIng2 = nvoCont2;*/
				
			}
		});
		add(filtroIng);
		
		
		add(contIng1 = generarListaIng(estados,"contIng", false));
		add(contIng2 = generarListaIng(estados,"contIng2", true));
		
	}
	
	private MarkupContainer generarListaIng(final ArrayList<Estado<T>> productos, String containerID, boolean estad){
		
		final MarkupContainer contenedor = new MarkupContainer(containerID){};
		
		RepeatingView ingRep = new RepeatingView("ingSel");
		for (final Estado<T> prod : productos){
			if(prod.isElegido() == estad){
			final AjaxLink item = new AjaxLink(ingRep.newChildId()){
				@Override
				public void onClick(AjaxRequestTarget target) {
					// TODO Auto-generated method stub
					prod.alternar();
					
					MarkupContainer nvoCont1;
					contIng1.replaceWith(nvoCont1 = generarListaIng(productos,"contIng", false));
					target.add(nvoCont1);
					contIng1 = nvoCont1;
					
					MarkupContainer nvoCont2;
					contIng2.replaceWith(nvoCont2 = generarListaIng(productos,"contIng2", true));
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
	
	private class Estado<T>{
		private T object;
		private boolean elegido;
		public Estado(T obj) {
			// TODO Auto-generated constructor stub
			object = obj;
			elegido = false;
		}
		public T getObject() {
			return object;
		}
		public void setObject(T object) {
			this.object = object;
		}
		public boolean isElegido() {
			return elegido;
		}
		public void setElegido(boolean elegido) {
			this.elegido = elegido;
		}
		
		public boolean alternar(){
			return (elegido = !elegido);
		}
		
		public String getName(){
			if(object.getClass() == Ingredientes.class){
				return ((Ingredientes) object).getIngrediente();
			}
			if(object.getClass() == Condimentos.class){
				return ((Condimentos) object).getCondimento();
			}
			return "";
			}
	}
}