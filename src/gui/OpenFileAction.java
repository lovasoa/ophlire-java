package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import data.Database;

public class OpenFileAction implements ActionListener {
	private MainWindow mainWindow;

	public OpenFileAction(MainWindow mainWindow) {
		super();
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Database db = mainWindow.getDatabase();
		if (db != null) {
			try {
				mainWindow.getTableauRegles().getModel()
						.setListe(db.getListeRegles());
			} catch (SQLException e1) {
				mainWindow.displayError("Format invalide",
						"La base n'a pas le bon format" + e1.getMessage());
			}
		}
	}
}
