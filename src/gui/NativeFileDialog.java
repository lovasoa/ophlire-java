package gui;

import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;

import data.Database;

public class NativeFileDialog {
	public static File dialog(MainWindow parent, String description,
			boolean isSave, FilenameFilter filenameFilter, String directory) {
		FileDialog dialog = new FileDialog(parent, description);
		dialog.setMode(isSave ? FileDialog.SAVE : FileDialog.LOAD);
		dialog.setFilenameFilter(filenameFilter);
		dialog.setMultipleMode(false);
		dialog.setDirectory(directory);
		dialog.setVisible(true);
		String dir = dialog.getDirectory();
		String file = dialog.getFile();
		System.out.println("dir: " + dir + "\nfile: " + file);
		if (dir == null || file == null) {
			if (file != null) {
				parent.displayError("Aucun fichier",
						"Impossible de déterminer le dossier dans lequel est le fichier sélectionné");
			}
			return null;
		}
		return new File(dir, file);
	}

	public static File openDb(MainWindow parent, Database curDb) {
		String directory = (curDb == null) ? null : curDb.getFile().getParent();
		return dialog(parent,
				"Base de données de mots et règles orthographiques", false,
				new ExtensionFilter("db"), directory);
	}

	public static File saveMots(MainWindow parent, String previousDir) {
		return dialog(parent, "Fichier texte de mots", true,
				new ExtensionFilter("txt"), previousDir);
	}
}

class ExtensionFilter implements FilenameFilter {
	private String extension;

	public ExtensionFilter(String extension) {
		this.extension = extension;
	}

	@Override
	public boolean accept(File dir, String filename) {
		return filename.endsWith("." + extension);
	}
}