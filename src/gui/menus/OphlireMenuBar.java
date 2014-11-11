package gui.menus;

import gui.MainWindow;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class OphlireMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public OphlireMenuBar(MainWindow mainWindow) {
		super();

		// Build the first menu.
		JMenu menu;

		// File
		menu = new FileMenu(mainWindow);
		this.add(menu);
		// Help
		menu = new HelpMenu(mainWindow);
		this.add(menu);
	}

}
