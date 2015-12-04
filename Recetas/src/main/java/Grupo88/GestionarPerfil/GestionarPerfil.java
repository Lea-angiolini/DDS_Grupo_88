package Grupo88.GestionarPerfil;

import master.ErrorPage;
import master.RegisteredPage;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import Database.DAOUsuarios;
import Grupo88.AltaUsuario.PanelCampos;
import Grupo88.Inicio.Inicio;
import ObjetosDB.Usuario;

public class GestionarPerfil extends RegisteredPage {	
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private FrmModifUsuario frmModifUsuario;
	private DAOUsuarios daoUsuarios;
	public GestionarPerfil(){
		super();
		
		add(frmModifUsuario = new FrmModifUsuario("FrmModifUsuario"));
	}
	
	
	public class FrmModifUsuario extends Form<Object> {

		private static final long serialVersionUID = 1L;
		private Usuario usuario = getUsuarioActual();
		private MarkupContainer error;
		public FrmModifUsuario(String id) {
			super(id);			
			//setDefaultModel(new CompoundPropertyModel(this));
			
			add(error = new MarkupContainer("error"){
				private static final long serialVersionUID = 6315760448959379801L;});
			
			error.setVisible(false);
			error.add(new FeedbackPanel("feedback"));
			error.setOutputMarkupId(true);
			
			add(new PanelCampos("campos", getSessionBD(), usuario));
		    
		    add(new Link<Object>("cancelar"){
				
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
				
					setResponsePage(Inicio.class);
					
				}
			});	
		   
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			
			daoUsuarios = new DAOUsuarios(getSessionBD());
			try {
				try{
				daoUsuarios.saveOrUpdate(usuario);
				setResponsePage(new ErrorPage("Su perfil ha sido actualizado"));
				}
				catch(javax.validation.ConstraintViolationException cve){
					info(cve.getConstraintViolations().iterator().next().getMessage());
				}
				catch (org.hibernate.exception.ConstraintViolationException cve) {
					info(cve.getMessage());
				}
				finally{
				error.setVisible(true);
				}
			} catch (Exception e) {
				setResponsePage(new ErrorPage("Parece que hubo un error. Intentelo mas tarde "));
				
			}
		}
	}
}
