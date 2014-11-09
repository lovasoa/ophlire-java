package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.Database;
import data.DbListWordsRequest;

public class ActionExportMot implements ActionListener {
	private MainWindow mainWindow;

	public ActionExportMot(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Choix d'un fichier de pour la sauvegarde
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Fichier de sauvegarde des mots");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Fichier texte",
				"txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(mainWindow);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Database db = mainWindow.getDatabase();
			try {
				db.exportMatching(chooser.getSelectedFile(),
						mainWindow.getTableauRegles().getModel().getListe(),
						new DbListWordsRequest());
			} catch (FileNotFoundException | SQLException e1) {
				mainWindow.displayError("Erreur lors de l'exportation",
						"L'exportation a échoué. Message de la base de données:\n"+e1.getMessage());
			}
		}
	}

}
