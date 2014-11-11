package gui.menus;

import gui.MainWindow;
import gui.actions.ActionCodes;
import gui.actions.ActionFileOpen;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class HelpMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;

	public HelpMenu(MainWindow mainWindow) {
		super("Aide");
		this.mainWindow = mainWindow;
		createMenuItems();
	}

	private void createMenuItems() {
		// a group of JMenuItems
		JMenuItem codes = new JMenuItem("Codes phonémiques", KeyEvent.VK_C);
		codes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA,
				ActionEvent.CTRL_MASK));
		codes.addActionListener(new ActionCodes(mainWindow));
		add(codes);
	}
	
}
