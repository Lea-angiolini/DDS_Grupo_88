package Grupo88.AltaUsuario;

import javax.swing.JOptionPane;

import master.ErrorPage;
import master.MasterPage;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import Database.DAOUsuarios;
import Grupo88.Inicio.Inicio;

public class AltaUsuario extends MasterPage {	
	

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private FrmAltaUsuario frmAltaUsuario;
	
	public AltaUsuario(){
		super();
		
		add(frmAltaUsuario = new FrmAltaUsuario("FrmAltaUsuario"));
	
	}
	
	private class FrmAltaUsuario extends Form<Object> {

		private static final long serialVersionUID = 1L;
		private final MarkupContainer error;
		private DAOUsuarios daoUsuarios;
		private PanelCampos panelCampos;
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public FrmAltaUsuario(String id) {
			super(id);		
			
			add(error = new MarkupContainer("error"){
				private static final long serialVersionUID = 6315760448959379801L;});
			
			error.setVisible(false);
			error.add(new FeedbackPanel("feedback"));
			error.setOutputMarkupId(true);
			
			add(panelCampos = new PanelCampos("campos", getSessionBD()));
		   		    
		    add(new Link<Object>("cancelar"){
				private static final long serialVersionUID = 8895339676060670334L;

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
				daoUsuarios.save(panelCampos.getUsuario());
				setResponsePage(new ErrorPage("Ha sido registrado satisfactoriamente \n Ya puede iniciar sesion"));
				}
				catch(javax.validation.ConstraintViolationException cve){
					info(cve.getConstraintViolations().iterator().next().getMessage());
				}
				catch (org.hibernate.exception.ConstraintViolationException cve) {
					//TODO ver tema errores controlar en la DB (ej este usuario ya existe)
					info(cve.getMessage());
				}
				finally{
				error.setVisible(true);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				setResponsePage(new ErrorPage("Parece que hubo un error. Intentelo mas tarde "));
				
			}
		}
	}
}