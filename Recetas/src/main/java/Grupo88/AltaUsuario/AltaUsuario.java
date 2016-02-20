package Grupo88.AltaUsuario;

import master.ErrorPage;
import master.MasterPage;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

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
		private PanelCampos panelCampos;
		private NegocioAltaUsuario negocio;

		public FrmAltaUsuario(String id) {
			super(id);		
			
			negocio = new NegocioAltaUsuario(getSessionUser());
			
			add(error = new MarkupContainer("error"){
					private static final long serialVersionUID = 6315760448959379801L;
				});
			
			error.setVisible(false);
			error.add(new FeedbackPanel("feedback"));
			error.setOutputMarkupId(true);
			
			
			add(panelCampos = new PanelCampos("campos", negocio));
		   		    
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
			
			if(negocio.guardarUsuario(panelCampos)){
				setResponsePage(new ErrorPage("Ha sido registrado satisfactoriamente. Ya puede iniciar sesion"));
				return;
			}

			error.setVisible(true);
			info(negocio.getError());
		}
	}
}
