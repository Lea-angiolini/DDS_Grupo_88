package Grupo88.GestionarGrupos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import master.ErrorPage;
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

import Database.DAOGrupos;
import Database.DAOUsuarios;
import Database.DBExeption;
import Grupo88.Detalles.DetalleGrupo;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Grupo;

public class GestionarGrupos extends MasterPage {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrmGestionarGrupos frmGestionarGrupos;
	private DAOGrupos daogrupos;
	public GestionarGrupos(){
		super();
		daogrupos = new DAOGrupos(getSessionBD());
		add(frmGestionarGrupos = new FrmGestionarGrupos("frmGestionarGrupos"));

	}
	
	private class FrmGestionarGrupos extends Form<Object> {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FrmGestionarGrupos(String id) {
			super(id);		
			setOutputMarkupId(true);

			add(new EmptyPanel("areaGrupos"));
			
			add(new Link<Object>("cancelar"){
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
				
					setResponsePage(Inicio.class);
					
				}
			});	
			
			add(new Link<Object>("todosGrupos"){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					
					
		        	List<Grupo> todosGrupos = null;
					try {
						todosGrupos = (List<Grupo>) daogrupos.findAll();
					} catch (DBExeption e) {
						e.printStackTrace();
					};
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
			
			add(new Link<Object>("btnMisGrupos"){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					
		        	if (!getUsuarioActual().getMisGrupos().isEmpty())
		        	{
		        		frmGestionarGrupos.addOrReplace(new FragmentoMisGrupos("areaGrupos", "fragmentGrupos", frmGestionarGrupos, new ArrayList<Grupo>( getUsuarioActual().getMisGrupos())).setOutputMarkupId(true).setOutputMarkupId(true));
		        	}
		        	else
		        	{
		        		frmGestionarGrupos.addOrReplace(new Label("areaGrupos","Usteded no tiene grupos"));
		        	}
		        }
			});
			
			add(new Link<Object>("btnCrearGrupo"){

				private static final long serialVersionUID = 5863814327655189500L;

				@Override
				public void onClick() {
					frmGestionarGrupos.addOrReplace(new FragmentoGrupoNuevo ("areaGrupos", "fragmentCrearGrupo", frmGestionarGrupos));
				}
			});
			
			
		}
	}
	
	private class FragmentoMisGrupos extends Fragment {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FragmentoMisGrupos(String id, String markupId, MarkupContainer markupPorvider, List<Grupo> grupos) {
        	
        	super(id, markupId, markupPorvider);
    		
        	//markupPorvider.remove(id);
        	
        	RepeatingView iterGrupos = new RepeatingView("iterGrupos");
        	
        		int i = 0;
        		//for (int i = 0; i < grupos.size(); i++)
				
				for (Grupo grupo:grupos) {
					
					
					AbstractItem item = new AbstractItem(iterGrupos.newChildId());
					Grupo grupoActual = grupo;

					item.add(new Label("nombre", grupoActual.getNombre()));
					item.add(new Label("creador", grupoActual.getCreador().getUsername()));
					item.add(new Label("detalle", grupoActual.getDetalle()));
					item.add(new Botones("botones", i, grupoActual));
					iterGrupos.add(item);
					
					i++;
				}
				add(iterGrupos);
				iterGrupos.setOutputMarkupId(true);
        	}
        	
            
        
	}
	
	private class FragmentoGrupoNuevo extends Fragment {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FragmentoGrupoNuevo (String id, String markupId, MarkupContainer markupPorvider){
			super (id, markupId, markupPorvider);
			
			final FragmentoGrupoNuevo esteFrag = this;
			final Grupo nuevoGrupo = new Grupo(0,"","");
			add(new TextField<String>("nombreGrupoNuevo", new PropertyModel<String>(nuevoGrupo, "nombre")));
			add(new TextField<String>("detalleGrupoNuevo", new PropertyModel<String>(nuevoGrupo, "detalle")));
			add(new EmptyPanel("etiquetaConf"));
			add(new Button("btnConfirmarNvoGrupo") {
	
				private static final long serialVersionUID = -2840007213453451497L;

				@Override
				public void onSubmit() {
					String msg = "";
					try{
					try {
						nuevoGrupo.setCreador(getUsuarioActual());
						daogrupos.saveOrUpdate(nuevoGrupo);
						msg = "Su grupo ha sido creado!";
						getSessionBD().refresh(getUsuarioActual());
					}
					catch (javax.validation.ConstraintViolationException cve){
						msg = cve.getConstraintViolations().iterator().next().getMessage();
						}
					catch (org.hibernate.exception.ConstraintViolationException cve){
						msg = cve.getMessage();
						}
					
					}
					catch (Exception e) {
						setResponsePage(new ErrorPage("Parece que hubo un error intentalo mas tarde"+e.getMessage()));
					}
				
					esteFrag.addOrReplace(new Label("etiquetaConf", msg ) );

				}
				
			});
		}
	}
	
	
	private class Botones extends MarkupContainer{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		final String labelActual;
		final String verActual;
		final Label label;
		final Link<?> verGrupo;
		AjaxLink<?> entrarsalir;
		
		public Botones(String id, final int i, final Grupo grupoActual) {
			
			super(id);
			labelActual = "label"+i;
			verActual = "verGrupo"+i;
			
			verGrupo = new Link<Object>("verGrupo") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					final PageParameters pars = new PageParameters();
					pars.add("idGrupo",grupoActual.getIdGrupo());
					setResponsePage(DetalleGrupo.class,pars);
				}
			};
			
			if(grupoActual.getUsuarios().contains(getUsuarioActual())){
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
			
			entrarsalir = new AjaxLink<Object>("entrar/salir") {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget target) {
		            
					if(grupoActual.getUsuarios().contains(getUsuarioActual())){
						grupoActual.setUsuario(getUsuarioActual());
						try {
							daogrupos.saveOrUpdate(grupoActual);
							target.appendJavaScript("noAdherido('"+i+"');");
						} catch (Exception/*DBExeption*/ e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
							e.printStackTrace();
							grupoActual.getUsuarios().remove(getUsuarioActual());
						}
					}
					else{
						if(grupoActual.getUsuarios().remove(getUsuarioActual())){
							try {
								daogrupos.saveOrUpdate(grupoActual);
								target.appendJavaScript("adherido('"+i+"');");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}						
					}

				}
			};
			
			entrarsalir.add(label);
			add(entrarsalir);
			add(verGrupo);
		}
	}
}