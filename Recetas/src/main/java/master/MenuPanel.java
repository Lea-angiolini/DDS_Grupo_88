package master;



import org.apache.wicket.markup.html.panel.Panel;

import Grupo88.PanelLogin;

public class MenuPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuPanel(String id) {
		super(id);
		add(new PanelLogin("loginPanel"));
	}
}
