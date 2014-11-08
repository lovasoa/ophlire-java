import gui.MainWindow;

import javax.swing.SwingUtilities;

public class Ophlire {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(MainWindow.getRunnable());
	}
}
