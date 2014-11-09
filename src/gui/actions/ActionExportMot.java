package gui.actions;

import gui.MainWindow;
import gui.PanelDico;
import gui.TableauRegles;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.Database;
import data.DbListWordsRequest;

public class ActionExportMot extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;

	public ActionExportMot(MainWindow mainWindow) {
		super("Exporter");
		this.mainWindow = mainWindow;
        putValue(SHORT_DESCRIPTION, "Exporter les mots");
        putValue(LONG_DESCRIPTION, "Exporte les mots qui utilisent les règles sélectionnées");
        putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
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
				PanelDico panelDico = mainWindow.getPanelDico();
				DbListWordsRequest req = new DbListWordsRequest(
						Integer.parseInt(panelDico.getTxtFieldNbLettresMin().getText()),
						Integer.parseInt(panelDico.getTxtFieldNbLettresMax().getText())
						);
				TableauRegles tableauRegles = mainWindow.getTableauRegles();
				db.exportMatching(chooser.getSelectedFile(),
						tableauRegles.getListeRegles(),
						tableauRegles.getSelectedRegles(),
						req);
			} catch (FileNotFoundException | SQLException e1) {
				mainWindow.displayError("Erreur lors de l'exportation",
						"L'exportation a échoué. Message de la base de données:\n"+e1.getMessage());
			}
		}
	}

}
