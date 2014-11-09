package gui;

import gui.actions.ActionExportMot;
import gui.actions.ActionFileOpen;
import gui.actions.ActionFileSave;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class FileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;

	public FileMenu(MainWindow mainWindow) {
		super("Fichier");
		this.mainWindow = mainWindow;
		createMenuItems();
	}

	private void createMenuItems() {
		// a group of JMenuItems
		JMenuItem ouvrir = new JMenuItem("Ouvrir", KeyEvent.VK_O);
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		ouvrir.addActionListener(new ActionFileOpen(mainWindow));
		add(ouvrir);
		
		JMenuItem save = new JMenuItem("Sauvegarder", KeyEvent.VK_S);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		save.addActionListener(new ActionFileSave(mainWindow));
		add(save);

		JMenuItem export = new JMenuItem("Exporter", KeyEvent.VK_E);
		save.addActionListener(new ActionExportMot(mainWindow));
		add(export);
	}
	
}
