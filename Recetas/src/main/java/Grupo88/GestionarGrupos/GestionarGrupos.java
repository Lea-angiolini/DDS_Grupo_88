package Grupo88.GestionarGrupos;

import java.util.ArrayList;

import master.ErrorPage;
import master.MasterPage;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.Model;

import Grupo88.Inicio.Inicio;
import ObjetosDB.Grupo;


public class GestionarGrupos extends MasterPage {	
	
	private static final long serialVersionUID = 1L;
	private FrmGestionarGrupos frmGestionarGrupos;
	private NegocioGrupos negocio;
	private TextField<String> buscar;
	private Model<String> textoBuscar;
	
	public GestionarGrupos(){
		super();
		negocio = new NegocioGrupos(getSessionUser());
		textoBuscar = new Model<String>();
		
		add(frmGestionarGrupos = new FrmGestionarGrupos("frmGestionarGrupos"));
	}
	
	private class FrmGestionarGrupos extends Form<Object> {
		
		private static final long serialVersionUID = 1L;

		public FrmGestionarGrupos(String id) {
			super(id);		
			setOutputMarkupId(true);
			
			add(new EmptyPanel("areaGrupos").setOutputMarkupId(true));
			
			add(new Link<Object>("cancelar"){
				
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
				
					setResponsePage(Inicio.class);
					
				}
			});	
			
        	if (!negocio.getTodosGrupos().isEmpty())
        		addOrReplace(new VistaGrupos("areaGrupos", new ArrayList<Grupo>( negocio.getTodosGrupos())).setOutputMarkupId(true));
        	else
        		addOrReplace(new Label("areaGrupos","No existen grupos"));
			
        	final Label checkLabel = new Label("elegirLabel","Ver grupos que estoy adherido");
        	checkLabel.setOutputMarkupId(true);
        	
			AjaxCheckBox check = new AjaxCheckBox("elegir", Model.of(Boolean.FALSE)) {
				
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					VistaGrupos fragmento;
					
					if(getModelObject()){
						fragmento = new VistaGrupos("areaGrupos", new ArrayList<Grupo>(getUsuarioActual().getGrupos()));
						checkLabel.setDefaultModelObject("Ver todos los grupos");
					}
					else{
						fragmento = new VistaGrupos("areaGrupos", new ArrayList<Grupo>(negocio.getGruposCon(textoBuscar.getObject())));
						checkLabel.setDefaultModelObject("Ver grupos que estoy adherido");
					}
					fragmento.setOutputMarkupId(true);
					frmGestionarGrupos.addOrReplace(fragmento);
					target.add(fragmento);
					target.add(checkLabel);
				}
				
			};
			
			check.setOutputMarkupId(true);
			
			if(getUsuarioActual().getGrupos().size() < 1){
				check.setEnabled(false);
				checkLabel.setDefaultModelObject("Usted no esta adherido a ningun grupo");
			}
			add(check);
			add(checkLabel);
			
			
			add(buscar = new TextField("buscar", textoBuscar));
			buscar.add(new AjaxFormComponentUpdatingBehavior ("onkeyup"){
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					VistaGrupos nuevaVista;
					frmGestionarGrupos.addOrReplace(nuevaVista = new VistaGrupos("areaGrupos", new ArrayList<Grupo>( negocio.getGruposCon(textoBuscar.getObject()))));
					target.add(nuevaVista);
				}
			});
		}
	}
}
