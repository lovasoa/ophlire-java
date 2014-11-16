package gui.actions;

import gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import data.Database;

public class ActionFileOpen implements ActionListener {
	private MainWindow mainWindow;

	public ActionFileOpen(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Database db = mainWindow.getNewDatabase();
		if (db != null) {
			try {
				mainWindow.getTableauRegles().getModel()
						.setListe(db.getListeRegles());
			} catch (SQLException e1) {
				mainWindow.displayError("Format invalide",
						"La base " + db.getFile() + " n'a pas le bon format: \n"
								+ e1.getMessage());
			}
		}
	}
}
