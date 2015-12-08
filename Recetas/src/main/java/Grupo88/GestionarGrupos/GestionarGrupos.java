package Grupo88.GestionarGrupos;

import java.util.ArrayList;
import java.util.List;

import master.ErrorPage;
import master.MasterPage;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import Database.DAOGrupos;
import Database.DAOUsuarios;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Grupo;


public class GestionarGrupos extends MasterPage {	
	
	private static final long serialVersionUID = 1L;
	private FrmGestionarGrupos frmGestionarGrupos;
	private DAOGrupos daogrupos;
	private DAOUsuarios daoUsuarios;
	
	public GestionarGrupos(){
		super();
		daogrupos = new DAOGrupos(getSessionBD());
		daoUsuarios = new DAOUsuarios(getSessionBD());
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
			
			final List<Grupo> todosGrupos;
			
			try {
				todosGrupos = (List<Grupo>) daogrupos.findAll();
			} catch (Exception e) {
				e.printStackTrace();
				setResponsePage(ErrorPage.ErrorCargaDatos());
				return;
			};
			
        	if (todosGrupos != null)
        	{
        		addOrReplace(new FragmentoMisGrupos("areaGrupos", "fragmentGrupos", frmGestionarGrupos, new ArrayList<Grupo>( todosGrupos)).setOutputMarkupId(true));
        	}
        	else
        	{
        		addOrReplace(new Label("areaGrupos","No existen grupos"));
        	}
			
        	final Label checkLabel = new Label("elegirLabel","Ver grupos que estoy adherido");
        	checkLabel.setOutputMarkupId(true);
        	
			AjaxCheckBox check = new AjaxCheckBox("elegir", Model.of(Boolean.FALSE)) {
				
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					FragmentoMisGrupos fragmento;
					
					if(getModelObject()){
						fragmento = new FragmentoMisGrupos("areaGrupos", "fragmentGrupos", frmGestionarGrupos, new ArrayList<Grupo>( getUsuarioActual().getMisGrupos()));
						checkLabel.setDefaultModelObject("Ver todos los grupos");
					}
					else{
						fragmento = new FragmentoMisGrupos("areaGrupos", "fragmentGrupos", frmGestionarGrupos, new ArrayList<Grupo>( todosGrupos));
						checkLabel.setDefaultModelObject("Ver grupos que estoy adherido");
					}
					fragmento.setOutputMarkupId(true);
					frmGestionarGrupos.addOrReplace(fragmento);
					target.add(fragmento);
					target.add(checkLabel);
				}
				
			};
			
			check.setOutputMarkupId(true);
			
			if(getUsuarioActual().getMisGrupos().size() < 1){
				check.setEnabled(false);
				checkLabel.setDefaultModelObject("Usted no esta adherido a ningun grupo");
			}
			add(check);
			add(checkLabel);
			
		}
	}
	
	private class FragmentoMisGrupos extends Fragment {

		private static final long serialVersionUID = 1L;

		public FragmentoMisGrupos(String id, String markupId, MarkupContainer markupPorvider, List<Grupo> grupos) {
        	
        	super(id, markupId, markupPorvider);
        	add(new VistaGrupos("vista", new ArrayList<Grupo>(grupos)));
		}
	}
}
