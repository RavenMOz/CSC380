package application.swing.projectinterface.subcomponents;

import application.swing.projectinterface.actions.Next;

public class PopupPane extends OverlayPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6027329497141147163L;

	
	public PopupPane(String text) {
		
		super();
		
		resize();
					
		String[] labels = text.split(";");
		
		for (String s : labels) {
			add(getFormattedLabel(s));
		}
		add(new Button("OK", new Next(this)));
				
	}
	
}
