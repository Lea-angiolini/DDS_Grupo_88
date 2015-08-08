package Grupo88;

import java.lang.ref.Reference;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;

import javax.jws.WebParam.Mode;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import master.MasterPage;
import objetosWicket.ModelUsuario;
import objetosWicket.SesionUsuario;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Response;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.eclipse.jetty.server.Server;
import org.apache.wicket.Session;

import Database.Browser;
import Grupo88.Login.FrmLogin;
import ObjetosDB.Complexiones;
import ObjetosDB.CondicionesPreexistentes;
import ObjetosDB.Dietas;
import ObjetosDB.Grupo;
import ObjetosDB.Recetas;
import ObjetosDB.Rutinas;
import ObjetosDB.Usuario;
import ObjetosDB.itemsABuscar;

public class GestionarGrupos extends MasterPage {	
	
	private FrmGestionarGrupos frmGestionarGrupos;
	private SesionUsuario sesion = (SesionUsuario)getSession();
	private Usuario user = sesion.getUsuario().getObject();
	
	public GestionarGrupos(){
		super();
		//getMenuPanel().setVisible(false);
		
		add(frmGestionarGrupos = new FrmGestionarGrupos("frmGestionarGrupos"));
		
		frmGestionarGrupos.add(new EmptyPanel("areaGrupos"));
		
		frmGestionarGrupos.add(new Link("cancelar"){
			
			@Override
			public void onClick() {
			
				setResponsePage(Inicio.class);
				
			}
		});	
		
		frmGestionarGrupos.add(new Link("todosGrupos"){
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				
	        	user.cargarGrupos();
	        	List<Grupo> todosGrupos = Browser.cargarGrupos("");
	        	if (!todosGrupos.isEmpty())
	        	{
	        		frmGestionarGrupos.addOrReplace(new FragmentoMisGrupos("areaGrupos", "fragmentGrupos", frmGestionarGrupos, todosGrupos));
	        	}
	        	else
	        	{
	        		frmGestionarGrupos.addOrReplace(new Label("areaGrupos","No existen grupos"));
	        	}
	        }
		});
		
		frmGestionarGrupos.add(new Link("btnMisGrupos"){
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				
	        	user.cargarGrupos();
	        	if (!user.getGrupos().isEmpty())
	        	{
	        		frmGestionarGrupos.addOrReplace(new FragmentoMisGrupos("areaGrupos", "fragmentGrupos", frmGestionarGrupos, user.getGrupos()));
	        	}
	        	else
	        	{
	        		frmGestionarGrupos.addOrReplace(new Label("areaGrupos","Usteded no tiene grupos"));
	        	}
	        }
		});
		
		frmGestionarGrupos.add(new Link("btnCrearGrupo"){
			@Override
			public void onClick() {
				frmGestionarGrupos.addOrReplace(new FragmentoGrupoNuevo ("areaGrupos", "fragmentCrearGrupo", frmGestionarGrupos));
			}
		});
		

	}
	
	public class FrmGestionarGrupos extends Form {

		private Usuario usuario = sesion.getUsuario().getObject();
		
		@SuppressWarnings("unchecked")
		public FrmGestionarGrupos(String id) {
			super(id);			
			//setDefaultModel(new CompoundPropertyModel(this));
		
			
			
		}
	}
	
	public class FragmentoMisGrupos extends Fragment {
        public FragmentoMisGrupos(String id, String markupId, MarkupContainer markupPorvider, List<Grupo> grupos) {
        	
        	super(id, markupId, markupPorvider);
    		
        	//markupPorvider.remove(id);
        	
        	RepeatingView iterGrupos = new RepeatingView("iterGrupos");
        	
				for (int i = 0; i< grupos.size(); i++) {
					
					AbstractItem item = new AbstractItem(iterGrupos.newChildId());
					
					final Grupo grupoActual = grupos.get(i);
					//final String btnActual = "btn"+i;
					final String labelActual = "label"+i;
					AjaxLink entrarsalir = new AjaxLink("entrar/salir") {
						
						
						protected String actualizar(){
							
							String clase;
							String texto;
							
							if(esta = user.estaEnGrupo(grupoActual)){
								clase="btn btn-danger";
								texto="Salir";
							}
							else
							{
								clase="btn btn-success";
								texto="Adherirse";
								//modeloBtn.setObject("document.getElementById('"+actual+"').innerHTML = Unirse;");
							}
					
							return "document.getElementById('"+labelActual+"').innerHTML = '"+texto+"';" +
							 "document.getElementById('"+labelActual+"').className = '"+clase+"';";
						}
						
						boolean esta;
						
						@Override
						protected void onBeforeRender() {
							// TODO Auto-generated method stub
							super.onBeforeRender();
							
							String clase;
							String texto;
							
							if(esta = user.estaEnGrupo(grupoActual)){
								clase="btn btn-danger";
								texto="Salir";
							}
							else
							{
								clase="btn btn-success";
								texto="Adherirse";	
							}
							
							Label label = new Label("textoEntrar/salir",texto);
							label.add(new AttributeAppender("id",labelActual));
							label.add(new AttributeAppender("class",clase));
							add(label);
						}
						
						@Override
						public void onClick(AjaxRequestTarget target) {
							// TODO Auto-generated method stub
				            
							if(esta){
								grupoActual.sacarUsuario(user);
							}
							else{
								grupoActual.agregarUsuario(user);
							}
							//add(new AttributeAppender("onclick", ));
							
							target.appendJavaScript(actualizar());
							//render();
						}
					};
					
					item.add(new Label("nombre", grupoActual.getNombre()));
					item.add(new Label("creador", grupoActual.getCreador()));
					item.add(new Label("detalle", grupoActual.getDetalle()));
					//entrarsalir.add(new AttributeAppender("id",btnActual));
					item.add(entrarsalir);
					iterGrupos.add(item);
					
					
				}
				add(iterGrupos);
        	}
        	
            
        
	}
	
	public class FragmentoGrupoNuevo extends Fragment {
		public FragmentoGrupoNuevo (String id, String markupId, MarkupContainer markupPorvider){
			super (id, markupId, markupPorvider);
			
			final FragmentoGrupoNuevo esteFrag = this;
			final Grupo nuevoGrupo = new Grupo(0,"",user.getUsername(),"");
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
}