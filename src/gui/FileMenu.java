package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.Database;

public class FileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	public FileMenu() {
		super("Fichier");
		createMenuItems();
	}

	private void createMenuItems() {

		// a group of JMenuItems
		JMenuItem ouvrir = new JMenuItem("Ouvrir", KeyEvent.VK_O);
		ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		ouvrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Choix d'un fichier de base de données
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Base de données SQLite de dictionnaire et règles de prononciation",
						"db");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						Database db = new Database(chooser.getSelectedFile());
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(getParent(),
							    "Impossible d'ouvrir la base de données:\n" + e1.getMessage(),
							    "Erreur à l'ouverture de la base",
							    JOptionPane.ERROR_MESSAGE);

					}
				}

			}

		});
		add(ouvrir);
	}
}
