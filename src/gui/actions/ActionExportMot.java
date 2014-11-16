package gui.actions;

import gui.MainWindow;
import gui.NativeFileDialog;
import gui.PanelDico;
import gui.TableauRegles;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import data.Database;
import data.DbListWordsRequest;

public class ActionExportMot extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static String previousDir;
	private MainWindow mainWindow;

	public ActionExportMot(MainWindow mainWindow) {
		super("Exporter");
		this.mainWindow = mainWindow;
		putValue(SHORT_DESCRIPTION, "Exporter les mots");
		putValue(LONG_DESCRIPTION,
				"Exporte les mots qui utilisent les règles sélectionnées");
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Choix d'un fichier de pour la sauvegarde
		File fichierMots = NativeFileDialog.saveMots(mainWindow, previousDir);
		if (fichierMots != null) {
			previousDir = fichierMots.getParent();
			Database db = mainWindow.getDatabase();
			try {
				PanelDico panelDico = mainWindow.getPanelDico();
				DbListWordsRequest req = new DbListWordsRequest(
						Integer.parseInt(panelDico.getTxtFieldNbLettresMin()
								.getText()), Integer.parseInt(panelDico
								.getTxtFieldNbLettresMax().getText()));
				TableauRegles tableauRegles = mainWindow.getTableauRegles();
				db.exportMatching(fichierMots, tableauRegles.getListeRegles(),
						tableauRegles.getSelectedRegles(), req);
			} catch (FileNotFoundException | SQLException e1) {
				mainWindow.displayError("Erreur lors de l'exportation",
						"L'exportation a échoué. Message de la base de données:\n"
								+ e1.getMessage());
			}
		}
	}

}
