package gui.actions;

import gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import data.Database;

public class ActionFileSave implements ActionListener {
	private MainWindow mainWindow;

	public ActionFileSave(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Database db = mainWindow.getDatabase();
		try {
			db.setListeRegles(mainWindow.getTableauRegles().getModel().getListe());
		} catch (SQLException e1) {
			mainWindow.displayError("Impossible de sauvegarder",
					"La base de données a retourné:\n" + e1.getMessage());
		}
	}

}
