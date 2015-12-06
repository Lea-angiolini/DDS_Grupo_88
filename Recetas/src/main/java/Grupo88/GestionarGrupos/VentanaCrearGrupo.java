package Grupo88.GestionarGrupos;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;

import com.googlecode.wicket.kendo.ui.widget.window.AbstractWindow;

class VentanaCrearGrupo extends AbstractWindow<Void>
{
	private static final long serialVersionUID = 1L;

	public VentanaCrearGrupo(String id, String title)
	{
		super(id, title, true);
		add(new Label("content","dfdfd"));
	}
	
	@Override
	protected void onOpen(AjaxRequestTarget target) {
		// TODO Auto-generated method stub
		super.onOpen(target);
	}
	@Override
	public boolean isCloseEventEnabled()
	{
		return true;
	}
}
