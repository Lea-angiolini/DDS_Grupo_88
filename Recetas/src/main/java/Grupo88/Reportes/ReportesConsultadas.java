package Grupo88.Reportes;

import java.util.List;

import javax.swing.JOptionPane;

import master.RegisteredPage;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.PropertyModel;

import Database.DAOReportes;
import ObjetosDB.Historial;

public class ReportesConsultadas extends RegisteredPage{
	

	private static final long serialVersionUID = 1L;
	private NegocioReportes negocio;

	public ReportesConsultadas() {
		add(new FormReporte("frmReportes"));
		negocio = new NegocioReportes(getSessionUser());
	}
	
	private class FormReporte extends Form<Object>{

		private static final long serialVersionUID = 1L;
		
		private TextField<String> pickerDesde;
		private TextField<String> pickerHasta;
		private String fechaDesde;
		private String fechaHasta;
		public FormReporte(String id) {
			super(id);
			pickerDesde = new TextField<String>("desde", new PropertyModel<String>(this, "fechaDesde"));
			pickerHasta = new TextField<String>("hasta", new PropertyModel<String>(this, "fechaHasta"));
			
			add(pickerDesde);
			add(pickerHasta);
			add(new EmptyPanel("AreaRecetas"));
		
		}
		
		@Override
		protected void onSubmit() {
			super.onSubmit();
			List<Historial> listaHistorial = negocio.recetasmasconsultadas(fechaDesde, fechaHasta);
		}
	}
}
