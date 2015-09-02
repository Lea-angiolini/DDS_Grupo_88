package Grupo88.GestionarGrupos;

import java.util.List;

import master.MasterPage;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import Database.Browser;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Grupo;

public class GestionarGrupos extends MasterPage {	
	
	private FrmGestionarGrupos frmGestionarGrupos;
	
	public GestionarGrupos(){
		super();
		
		add(frmGestionarGrupos = new FrmGestionarGrupos("frmGestionarGrupos"));

	}
	
	private class FrmGestionarGrupos extends Form {
		
		@SuppressWarnings("unchecked")
		public FrmGestionarGrupos(String id) {
			super(id);		
			setOutputMarkupId(true);

			add(new EmptyPanel("areaGrupos"));
			
			add(new Link("cancelar"){
				
				@Override
				public void onClick() {
				
					setResponsePage(Inicio.class);
					
				}
			});	
			
			add(new Link("todosGrupos"){
				@Override
				public void onClick() {
					// TODO Auto-generated method stub
					
		        	getUsuarioActual().cargarGrupos();
		        	List<Grupo> todosGrupos = Browser.cargarGrupos("");
		        	if (!todosGrupos.isEmpty())
		        	{
		        		frmGestionarGrupos.addOrReplace(new FragmentoMisGrupos("areaGrupos", "fragmentGrupos", frmGestionarGrupos, todosGrupos).setOutputMarkupId(true));
		        	}
		        	else
		        	{
		        		frmGestionarGrupos.addOrReplace(new Label("areaGrupos","No existen grupos"));
		        	}
		        }
			}.setOutputMarkupId(true));
			
			add(new Link("btnMisGrupos"){
				@Override
				public void onClick() {
					// TODO Auto-generated method stub
					
					getUsuarioActual().cargarGrupos();
		        	if (!getUsuarioActual().getGrupos().isEmpty())
		        	{
		        		frmGestionarGrupos.addOrReplace(new FragmentoMisGrupos("areaGrupos", "fragmentGrupos", frmGestionarGrupos, getUsuarioActual().getGrupos()).setOutputMarkupId(true).setOutputMarkupId(true));
		        	}
		        	else
		        	{
		        		frmGestionarGrupos.addOrReplace(new Label("areaGrupos","Usteded no tiene grupos"));
		        	}
		        }
			});
			
			add(new Link("btnCrearGrupo"){
				@Override
				public void onClick() {
					frmGestionarGrupos.addOrReplace(new FragmentoGrupoNuevo ("areaGrupos", "fragmentCrearGrupo", frmGestionarGrupos));
				}
			});
			
			
		}
	}
	
	private class FragmentoMisGrupos extends Fragment {
        public FragmentoMisGrupos(String id, String markupId, MarkupContainer markupPorvider, List<Grupo> grupos) {
        	
        	super(id, markupId, markupPorvider);
    		
        	//markupPorvider.remove(id);
        	
        	RepeatingView iterGrupos = new RepeatingView("iterGrupos");
        	
				for (int i = 0; i< grupos.size(); i++) {
					
					AbstractItem item = new AbstractItem(iterGrupos.newChildId());
					Grupo grupoActual = grupos.get(i);

					item.add(new Label("nombre", grupoActual.getNombre()));
					item.add(new Label("creador", grupoActual.getCreador()));
					item.add(new Label("detalle", grupoActual.getDetalle()));
					item.add(new Botones("botones", i, grupoActual));
					iterGrupos.add(item);
					
					
				}
				add(iterGrupos);
				iterGrupos.setOutputMarkupId(true);
        	}
        	
            
        
	}
	
	private class FragmentoGrupoNuevo extends Fragment {
		public FragmentoGrupoNuevo (String id, String markupId, MarkupContainer markupPorvider){
			super (id, markupId, markupPorvider);
			
			final FragmentoGrupoNuevo esteFrag = this;
			final Grupo nuevoGrupo = new Grupo(0,"",getUsuarioActual().getUsername(),"");
			add(new TextField<String>("nombreGrupoNuevo", new PropertyModel<String>(nuevoGrupo, "nombre")));
			add(new TextField<String>("detalleGrupoNuevo", new PropertyModel<String>(nuevoGrupo, "detalle")));
			add(new EmptyPanel("etiquetaConf"));
			add(new Button("btnConfirmarNvoGrupo") {
				@Override
				public void onSubmit() {
					// TODO Auto-generated method stub
					String msg;
					if (nuevoGrupo.agregarGrupo() > 0){
						msg = "Su grupo ha sido creado!";
					}
					else{
						msg = "Opss, parece que hubo un problema!";
					}
					esteFrag.addOrReplace(new Label("etiquetaConf", msg ) );

				}
				
			});
		}
	}
	
	
	private class Botones extends MarkupContainer{
		
		final String labelActual;
		final String verActual;
		final Label label;
		final Link verGrupo;
		AjaxLink entrarsalir;
		
		public Botones(String id, final int i, final Grupo grupoActual) {
			
			super(id);
			labelActual = "label"+i;
			verActual = "verGrupo"+i;
			
			verGrupo = new Link("verGrupo") {
				@Override
				public void onClick() {
					final PageParameters pars = new PageParameters();
					pars.add("idGrupo",grupoActual.getIdGrupo());
					setResponsePage(DetalleGrupo.class,pars);
				}
			};
			
			if(getUsuarioActual().estaEnGrupo(grupoActual)){
				label = new Label("textoEntrar/salir","Salir");
				label.add(new AttributeModifier("class","btn btn-danger"));
				verGrupo.add(new AttributeModifier("style", "visibility: visible"));
			}
			else{
				label = new Label("textoEntrar/salir","Adherirse");
				label.add(new AttributeModifier("class","btn btn-success"));
				verGrupo.add(new AttributeModifier("style", "visibility: hidden"));
			}
			
			label.add(new AttributeModifier("id",labelActual));
			verGrupo.add(new AttributeModifier("id",verActual));
			
			entrarsalir = new AjaxLink("entrar/salir") {
				
				@Override
				public void onClick(AjaxRequestTarget target) {
					// TODO Auto-generated method stub
		            
					if(getUsuarioActual().estaEnGrupo(grupoActual)){
						grupoActual.sacarUsuario(getUsuarioActual());
						target.appendJavaScript("noAdherido('"+i+"');");
					}
					else{
						grupoActual.agregarUsuario(getUsuarioActual());
						target.appendJavaScript("adherido('"+i+"');");
					}

				}
			};
			
			entrarsalir.add(label);
			add(entrarsalir);
			add(verGrupo);
		}
	}
}