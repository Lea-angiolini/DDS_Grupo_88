package Grupo88.Componentes;

import objetosWicket.SesionUsuario;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import Grupo88.BuscarReceta.BuscarReceta;
import Grupo88.Estadisticas.Estadisticas;
import Grupo88.Estadisticas.NegocioEstadistica;
import Grupo88.GestionarGrupos.CrearNuevoGrupo;
import Grupo88.GestionarGrupos.GestionarGrupos;
import Grupo88.GestionarPerfil.CambioPass;
import Grupo88.GestionarPerfil.GestionarPerfil;
import Grupo88.MisRecetas.MisRecetas;
import Grupo88.Reportes.ReporteNuevasRecetas;
import Grupo88.Reportes.ReporteSegunCalorias;
import Grupo88.Reportes.ReportesConsultadas;

public class PanelLinks extends Panel {

	private static final long serialVersionUID = -6539107446596515L;
	private SesionUsuario sesion;
	
	public PanelLinks(String id) {
		super(id);
		
		sesion = (SesionUsuario)getSession();
		add(new FrmLinks("FrmLinks"));
		
	}

	public class FrmLinks extends Form<Object> {
		
		private static final long serialVersionUID = -6452138117669215294L;

		public FrmLinks(String id) {
			super(id);
			
			add(new Link<Object>("recetas"){
				
				private static final long serialVersionUID = -3075762516203733660L;

				public void onClick() {
					setResponsePage(BuscarReceta.class);
				}
			});
			
			add(new Link<Object>("perfil"){
				

				private static final long serialVersionUID = 4731168038857564536L;

				public void onClick() {
					setResponsePage(GestionarPerfil.class);
				}
			});
			
			add(new Link<Object>("cambiarPass"){
				
				private static final long serialVersionUID = 729198405076274338L;

				public void onClick() {
					setResponsePage(CambioPass.class);
				}
			});
			
			add(new Link<Object>("buscarGrupos"){
				
				private static final long serialVersionUID = -3911787400601770294L;

				public void onClick() {
					setResponsePage(GestionarGrupos.class);
				}
			});
			
			add(new Link<Object>("CrearNuevoGrupo"){
				
				private static final long serialVersionUID = -3437881417857440333L;

				public void onClick() {
					setResponsePage(CrearNuevoGrupo.class);
				}
			});

			add(new Link<Object>("misRecetas"){
				
				private static final long serialVersionUID = 3555479200801290084L;

				public void onClick() {
					setResponsePage(MisRecetas.class);
				}
			});
			
			add(new Link<Object>("estadisticas"){
				
				private static final long serialVersionUID = 784853975450988914L;

				public void onClick() {
					setResponsePage(new Estadisticas(NegocioEstadistica.porSexo(sesion)));
				}
			});

			add(new Link<Object>("consultasDificultad"){
				
				private static final long serialVersionUID = -784860097012404958L;

				public void onClick() {
					setResponsePage(new Estadisticas(NegocioEstadistica.porDificultad(sesion)));
				}
			});
			
			add(new Link<Object>("topRecetasConsultadas"){
				
				private static final long serialVersionUID = 7496498129134328754L;

				public void onClick() {
					setResponsePage(new Estadisticas(NegocioEstadistica.recetasMasconsultadas(sesion)));
				}
			});
			
			add(new Link<Object>("recetasConsultadas"){
				
				private static final long serialVersionUID = 440071720954901808L;

				public void onClick() {
					setResponsePage(new ReportesConsultadas());
				}
			});
			
			add(new Link<Object>("recetasNuevas"){
				
				private static final long serialVersionUID = 2541285599971202210L;

				public void onClick() {
					setResponsePage(new ReporteNuevasRecetas());
				}
			});
			
			add(new Link<Object>("recetasCalorias"){
				
				private static final long serialVersionUID = 7288594960882148949L;

				public void onClick() {
					setResponsePage(new ReporteSegunCalorias());
				}
			});
			
		}
	}	
}