package Grupo88.Componentes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import ObjetosDB.AlimDeReceta;
import ObjetosDB.Condimentos;
import ObjetosDB.Ingredientes;

public class DropList<T extends AlimDeReceta> extends Panel {

	MarkupContainer contIng1;
	MarkupContainer contIng2;
	final ArrayList<Estado<T>> estados = new ArrayList<Estado<T>>();
	final Model<String> filtroIngText;
	
	public DropList(String id, ArrayList<T> lista) {
		super(id);
		
		for(T prod : lista){
			estados.add(new Estado<T>(prod));
		}
		
		filtroIngText = new Model<String>();
		final TextField<String> filtroIng = new TextField<String>("filtroIng", filtroIngText);
		filtroIng.add(new AjaxFormComponentUpdatingBehavior ("onkeyup") {
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
				//target.add(filtroIng);
				
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
		ArrayList<Estado<T>> filtrados = new ArrayList<Estado<T>>(); 
		
		if(filtro.getObject() == null){
			return productos;
		}
		
		for(Estado<T> prod : estados ){
			if(prod.getName().toLowerCase().startsWith(filtro.getObject().toLowerCase())){
				filtrados.add(prod);
			}
		}
		return filtrados;
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
					contIng1.replaceWith(nvoCont1 = generarListaIng(filtrarProductos(productos, filtroIngText),"contIng", false));
					target.add(nvoCont1);
					contIng1 = nvoCont1;
					
					MarkupContainer nvoCont2;
					contIng2.replaceWith(nvoCont2 = generarListaIng(filtrarProductos(productos, filtroIngText),"contIng2", true));
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
	
	
	private class Estado<T extends AlimDeReceta>{
		private T object;
		private boolean elegido;
		public Estado(T prod) {
			// TODO Auto-generated constructor stub
			object = prod;
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
			
			return object.getName();
			}
	}
}